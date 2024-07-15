package com.angelvt.foroHub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespuestaDatosRegistro(
        @NotNull
        Long autor,
        @NotNull
        Long topico,
        @NotBlank
        String mensaje) {
}
