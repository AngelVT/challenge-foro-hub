package com.angelvt.foroHub.domain.topic.validaciones;

import com.angelvt.foroHub.domain.topic.TopicoRepository;
import com.angelvt.foroHub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarTopicosDuplicados implements IValidarTopico {
    private final TopicoRepository topicoRepository;

    @Autowired
    public ValidarTopicosDuplicados(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @Override
    public void validar(DatosTopico datosTopico) {
        var topicosDB = topicoRepository.findAllByActivoTrue();

        var tituloNuevo = datosTopico.titulo().replace(" ", "").toLowerCase();
        var mensajeNuevo = datosTopico.mensaje().replace(" ", "").toLowerCase();

        topicosDB.forEach(t -> {
            var tituloDB = t.getTitulo().replace(" ", "").toLowerCase();
            var mensajeDB = t.getMensaje().replace(" ", "").toLowerCase();
            if (tituloDB.equals(tituloNuevo) || mensajeDB.equals(mensajeNuevo)){
                throw new IntegrityValidation("No se pueden duplicar topicos");
            }
        });
    }

}
