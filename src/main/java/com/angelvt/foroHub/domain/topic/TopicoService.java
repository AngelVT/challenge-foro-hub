package com.angelvt.foroHub.domain.topic;

import com.angelvt.foroHub.domain.curso.CursoRepository;
import com.angelvt.foroHub.domain.respuesta.Respuesta;
import com.angelvt.foroHub.domain.respuesta.RespuestaDatosListaTopico;
import com.angelvt.foroHub.domain.respuesta.RespuestaRepository;
import com.angelvt.foroHub.domain.topic.validaciones.DatosTopico;
import com.angelvt.foroHub.domain.topic.validaciones.IValidarTopico;
import com.angelvt.foroHub.domain.usuario.UsuarioRepository;
import com.angelvt.foroHub.infra.errors.IntegrityValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class TopicoService {
    private final UsuarioRepository usuarioRepository;
    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final RespuestaRepository respuestaRepository;

    private final List<IValidarTopico> validarTopicos;

    @Autowired
    public TopicoService(UsuarioRepository usuarioRepository, TopicoRepository topicoRepository, CursoRepository cursoRepository, RespuestaRepository respuestaRepository, List<IValidarTopico> validarTopicos) {
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
        this.respuestaRepository = respuestaRepository;
        this.validarTopicos = validarTopicos;
    }

    public Page<TopicoDatosRespuesta> obtenerTopicos(Pageable paginar) {
        return topicoRepository.findAllByActivoTrue(paginar).map(TopicoDatosRespuesta::new);
    }

    public List<TopicoDatosRespuesta> obtenerTopicosCurso(Long cursoId) {
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
            throw new EntityNotFoundException("El topico especificado no existe");
        }

        var topico = topicoRepository.getReferenceById(id);

        var respuestas = respuestaRepository.findByTopicoAndActivoTrue(topico).stream()
                .sorted(Comparator.comparing(Respuesta::getFechaCreacion))
                .map(RespuestaDatosListaTopico::new)
                .toList();

        return new TopicoDatosCompleto(topico, respuestas);
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

        validarTopicos.forEach(v -> v.validar(new DatosTopico(datos.titulo(), datos.mensaje())));

        LocalDateTime creacion = LocalDateTime.now();

        var topico = new Topico(null, datos.titulo(), datos.mensaje(), creacion, Estado.ABIERTO, autor, curso, true);

        topicoRepository.save(topico);

        return new TopicoDatosRespuesta(topico);
    }

    public TopicoDatosRespuesta actualizarTopico(Long id, TopicoDatosActualizar datos) {
        if (!topicoRepository.existsByIdAndActivoTrue(id)) {
            throw new EntityNotFoundException("El topico especificado no existe");
        }

        var topico = topicoRepository.getReferenceById(id);

        validarEstado(topico);

        validarTopicos.forEach(v -> v.validar(new DatosTopico(datos.titulo(), datos.mensaje())));

        topico.actualizar(datos);

        return new TopicoDatosRespuesta(topico);
    }

    public TopicoDatosRespuesta cerrarTopico(Long id, TopicoDatosActualizar datos) {
        if (!topicoRepository.existsByIdAndActivoTrue(id)) {
            throw new EntityNotFoundException("El topico especificado no existe");
        }

        var topico = topicoRepository.getReferenceById(id);

        validarEstado(topico);

        if (datos.estado().equals(Estado.CERRADO) || datos.estado().equals(Estado.CERRADO_RESUELTO)) {
            topico.setStatus(datos.estado());
        } else {
            throw new IntegrityValidation("Solo se puede cerrar un topico como cerrado o cerrado resuelto.");
        }

        return new TopicoDatosRespuesta(topico);
    }

    public void borrarTopico(Long id) {
        if (!topicoRepository.existsByIdAndActivoTrue(id)) {
            throw new EntityNotFoundException("El topico especificado no existe");
        }

        var topico = topicoRepository.getReferenceById(id);

        var respuestas = respuestaRepository.findByTopicoAndActivoTrue(topico);

        topico.eliminar();
        respuestas.forEach(Respuesta::eliminar);
        //Por logica de negocio no se elimina realmente el topico
        //topicoRepository.deleteById(id);
    }

    public static void validarEstado(Topico topico) {
        if (!topico.getStatus().equals(Estado.ABIERTO)) {
            throw new IntegrityValidation("El topico ya se ha cerrado como " + topico.getStatus());
        }
    }
}
