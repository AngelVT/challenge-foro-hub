package com.angelvt.foroHub.domain.topic;

import java.time.LocalDateTime;

public record TopicoDatosRespuesta(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado status,
        Long autor,
        Long curso
) {
    public TopicoDatosRespuesta(Topico topico) {
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(), topico.getFechaCreacion(),topico.getStatus(),topico.getAutor().getId(),topico.getCurso().getId());
    }
}
