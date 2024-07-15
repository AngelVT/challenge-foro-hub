package com.angelvt.foroHub.domain.topic;

import jakarta.validation.constraints.NotNull;

public record TopicoDatosActualizar(
        String titulo,
        String mensaje,
        Estado estado
) {
}
