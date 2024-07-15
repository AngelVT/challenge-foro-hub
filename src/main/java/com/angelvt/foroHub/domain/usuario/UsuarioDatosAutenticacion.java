package com.angelvt.foroHub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDatosAutenticacion(@NotBlank String email,
                                        @NotBlank String password) {
}
