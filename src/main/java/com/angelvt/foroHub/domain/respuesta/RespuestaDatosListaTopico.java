package com.angelvt.foroHub.domain.respuesta;

import java.time.LocalDateTime;

public record RespuestaDatosListaTopico(Long id,
                                        Long autor,
                                        String mensaje,
                                        LocalDateTime fechaPublicacion,
                                        Boolean solucion) {
    public RespuestaDatosListaTopico(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getAutor().getId(), respuesta.getMensaje(), respuesta.getFechaCreacion(), respuesta.getSolucion());
    }
}
