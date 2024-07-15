package com.angelvt.foroHub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDatosRegistro(@NotBlank String nombre,
                                   @NotBlank String email,
                                   @NotBlank String password) {
}
