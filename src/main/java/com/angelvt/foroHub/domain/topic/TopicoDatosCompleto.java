package com.angelvt.foroHub.domain.topic;

import com.angelvt.foroHub.domain.respuesta.Respuesta;
import com.angelvt.foroHub.domain.respuesta.RespuestaDatosListaTopico;

import java.time.LocalDateTime;
import java.util.List;

public record TopicoDatosCompleto(Long id,
                                  Long autor,
                                  Long curso,
                                  String titulo,
                                  String mensaje,
                                  Estado status,
                                  LocalDateTime fechaPublicacion,
                                  List<RespuestaDatosListaTopico> respuestas) {
    public TopicoDatosCompleto(Topico topico, List<RespuestaDatosListaTopico> respuestas) {
        this(topico.getId(), topico.getAutor().getId(),topico.getCurso().getId(),topico.getTitulo(),topico.getMensaje(), topico.getStatus(), topico.getFechaCreacion(), respuestas);
    }
}
