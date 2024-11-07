package com.microservice.service.model.inputs;

import java.util.Optional;

public record ProveedorInput(
        Optional<String> nombre,
        Optional<String> correo,
        Optional<Integer> edad,
        Optional<String> foto,
        Optional<String> descripcion,
        Optional<String> telefono,
        Optional<String> pagWeb,
        Optional<String> contactoRedes,
        Optional<String> alojamientoId,
        Optional<String> transportesId
) {

}
