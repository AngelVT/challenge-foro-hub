package com.angelvt.foroHub.domain.topic;

import java.time.LocalDateTime;

public record TopicoDatosRespuesta(
        Long id,
        Long autor,
        Long curso,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado status
) {
    public TopicoDatosRespuesta(Topico topico) {
        this(topico.getId(), topico.getAutor().getId(),topico.getCurso().getId(),topico.getTitulo(),topico.getMensaje(), topico.getFechaCreacion(),topico.getStatus());
    }
}
