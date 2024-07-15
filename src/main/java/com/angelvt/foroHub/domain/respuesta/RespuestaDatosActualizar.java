package com.angelvt.foroHub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

public record RespuestaDatosActualizar(@NotBlank String mensaje) {
}
