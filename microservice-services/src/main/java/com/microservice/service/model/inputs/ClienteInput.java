package com.microservice.service.model.inputs;

import java.util.Optional;

public record ClienteInput(
        Optional<String> nombre,
        Optional<String> correo,
        Optional<Integer> edad,
        Optional<String> foto,
        Optional<String> descripcion
) {
}
