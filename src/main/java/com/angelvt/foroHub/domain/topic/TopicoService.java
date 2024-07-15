package com.angelvt.foroHub.domain.topic;

import com.angelvt.foroHub.domain.curso.CursoRepository;
import com.angelvt.foroHub.domain.respuesta.Respuesta;
import com.angelvt.foroHub.domain.respuesta.RespuestaDatosListaTopico;
import com.angelvt.foroHub.domain.respuesta.RespuestaRepository;
import com.angelvt.foroHub.domain.usuario.UsuarioRepository;
import com.angelvt.foroHub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TopicoService {
    private final UsuarioRepository usuarioRepository;
    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final RespuestaRepository respuestaRepository;

    @Autowired
    public TopicoService(UsuarioRepository usuarioRepository, TopicoRepository topicoRepository, CursoRepository cursoRepository, RespuestaRepository respuestaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
        this.respuestaRepository = respuestaRepository;
    }

    public TopicoDatosRespuesta publicarTopico(TopicoDatosRegistro datos) {
        if (!cursoRepository.existsById(datos.curso())) {
            throw new IntegrityValidation("El curso especificado no existe");
        }

        if (!usuarioRepository.existsById(datos.autor())) {
            throw new IntegrityValidation("El usuario especificado no existe");
        }

        var curso = cursoRepository.getReferenceById(datos.curso());
        var autor = usuarioRepository.getReferenceById(datos.autor());

        LocalDateTime creacion = LocalDateTime.now();

        var topico = new Topico(null, datos.titulo(), datos.mensaje(), creacion, Estado.ABIERTO, autor, curso, true);

        topicoRepository.save(topico);

        return new TopicoDatosRespuesta(topico);
    }

    public List<TopicoDatosRespuesta> obtenerTopicos(Long cursoId) {
        if (!cursoRepository.existsById(cursoId)) {
            throw new IntegrityValidation("El curso especificado no existe");
        }

        var curso = cursoRepository.getReferenceById(cursoId);

        var topicos = topicoRepository.findByCursoAndActivoTrue(curso);

        return topicos.stream()
                .sorted(Comparator.comparing(Topico::getFechaCreacion).reversed())
                .map(TopicoDatosRespuesta::new)
                .toList();
    }

    public TopicoDatosCompleto obtenerTopico(Long id) {
        if (!topicoRepository.existsByIdAndActivoTrue(id)) {
            throw new IntegrityValidation("El topico especificado no existe");
        }

        var topico = topicoRepository.getReferenceById(id);

        var respuestas = respuestaRepository.findByTopico(topico).stream()
                .sorted(Comparator.comparing(Respuesta::getFechaCreacion).reversed())
                .map(RespuestaDatosListaTopico::new)
                .toList();

        return new TopicoDatosCompleto(topico, respuestas);
    }

    public TopicoDatosRespuesta actualizarTopico(Long id, TopicoDatosActualizar datos) {
        if (!topicoRepository.existsByIdAndActivoTrue(id)) {
            throw new IntegrityValidation("El topico especificado no existe");
        }

        var topico = topicoRepository.getReferenceById(id);

        if (!topico.getStatus().equals(Estado.ABIERTO)) {
            throw new IntegrityValidation("El topico ya se ha cerrado como " + topico.getStatus());
        }

        topico.actualizar(datos);

        return new TopicoDatosRespuesta(topico);
    }

    public TopicoDatosRespuesta cerrarTopico(Long id, TopicoDatosActualizar datos) {
        if (!topicoRepository.existsByIdAndActivoTrue(id)) {
            throw new IntegrityValidation("El topico especificado no existe");
        }

        var topico = topicoRepository.getReferenceById(id);

        if (!topico.getStatus().equals(Estado.ABIERTO)) {
            throw new IntegrityValidation("El topico ya se ha cerrado como " + topico.getStatus());
        }

        if (datos.estado().equals(Estado.CERRADO) || datos.estado().equals(Estado.CERRADO_RESUELTO)) {
            topico.setStatus(datos.estado());
        } else {
            throw new IntegrityValidation("Solo se puede cerrar un topico como cerrado o cerrado resuelto.");
        }

        return  new TopicoDatosRespuesta(topico);
    }

    public void borrarTopico(Long id) {
        if (id != null && !topicoRepository.existsByIdAndActivoTrue(id)) {
            throw new IntegrityValidation("El topico especificado no existe");
        }

        var topico = topicoRepository.getReferenceById(id);

        topico.eliminar();
    }
}
