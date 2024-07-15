package com.angelvt.foroHub.domain.respuesta;

import java.time.LocalDateTime;

public record RespuestaDatosCompleto(Long id,
                                     Long topico,
                                     Long autor,
                                     String mensaje,
                                     LocalDateTime fechaPublicacion,
                                     Boolean solucion) {
    public RespuestaDatosCompleto(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getTopico().getId(), respuesta.getAutor().getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getSolucion());
    }
}
