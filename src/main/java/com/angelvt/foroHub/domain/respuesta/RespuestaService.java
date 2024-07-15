package com.angelvt.foroHub.domain.respuesta;

import com.angelvt.foroHub.domain.topic.Estado;
import com.angelvt.foroHub.domain.topic.TopicoRepository;
import com.angelvt.foroHub.domain.topic.TopicoService;
import com.angelvt.foroHub.domain.usuario.UsuarioRepository;
import com.angelvt.foroHub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class RespuestaService {
    private final RespuestaRepository respuestaRepository;
    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public RespuestaService(RespuestaRepository respuestaRepository, TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.respuestaRepository = respuestaRepository;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<RespuestaDatosListaTopico> obtenerRespuestasTopico(Long id) {
        if (!topicoRepository.existsByIdAndActivoTrue(id)) {
            throw new IntegrityValidation("El topico no existe");
        }

        var topico = topicoRepository.getReferenceById(id);

        return respuestaRepository.findByTopicoAndActivoTrue(topico).stream()
                .sorted(Comparator.comparing(Respuesta::getFechaCreacion).reversed())
                .map(RespuestaDatosListaTopico::new)
                .toList();
    }

    public RespuestaDatosCompleto obtenerRespuesta(Long id) {
        if (!respuestaRepository.existsByIdAndActivoTrue(id)) {
            throw new IntegrityValidation("La respuesta no existe");
        }

        var respuesta = respuestaRepository.getReferenceById(id);

        return new RespuestaDatosCompleto(respuesta);
    }

    public RespuestaDatosCompleto publicarRespuesta(RespuestaDatosRegistro datos) {
        if (!usuarioRepository.existsById(datos.autor())) {
            throw new IntegrityValidation("El usuario especificado no existe");
        }

        if (!topicoRepository.existsByIdAndActivoTrue(datos.topico())) {
            throw new IntegrityValidation("El topico especificado no existe");
        }

        var topico = topicoRepository.getReferenceById(datos.topico());

        TopicoService.validarEstado(topico);

        var autor = usuarioRepository.getReferenceById(datos.autor());


        var respuesta = new Respuesta(autor,topico, datos.mensaje());

        respuestaRepository.save(respuesta);

        return new RespuestaDatosCompleto(respuesta);
    }

    public RespuestaDatosCompleto actualizarRespuesta(Long id, RespuestaDatosActualizar datosActualizar) {
        if (!respuestaRepository.existsByIdAndActivoTrue(id)) {
            throw new IntegrityValidation("La respuesta especificada no existe");
        }

        var respuesta = respuestaRepository.getReferenceById(id);

        respuesta.actualizar(datosActualizar.mensaje());

        return new RespuestaDatosCompleto(respuesta);
    }

    public RespuestaDatosCompleto marcarComoSolucion(Long id) {
        if (!respuestaRepository.existsByIdAndActivoTrue(id)) {
            throw new IntegrityValidation("La respuesta especificada no existe");
        }

        var respuesta = respuestaRepository.getReferenceById(id);

        var topico = topicoRepository.getReferenceById(respuesta.getTopico().getId());

        TopicoService.validarEstado(topico);

        respuesta.marcarSolucion(true);

        topico.setStatus(Estado.RESUELTO);

        return new RespuestaDatosCompleto(respuesta);
    }

    public void eliminarRespuesta(Long id) {
        if (!respuestaRepository.existsByIdAndActivoTrue(id)) {
            throw new IntegrityValidation("La respuesta especificada no existe");
        }

        var respuesta = respuestaRepository.getReferenceById(id);

        var topico = topicoRepository.getReferenceById(respuesta.getTopico().getId());

        TopicoService.validarEstado(topico);

        respuesta.eliminar();
        //Por logica de negocio no se elimina realmente el topico
        //respuestaRepository.deleteById(id);
    }
}
