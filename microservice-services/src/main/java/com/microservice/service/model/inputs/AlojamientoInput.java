package com.microservice.service.model.inputs;

import java.util.Optional;

public record AlojamientoInput(
        Optional<String> nombre,
        Optional<String> ubicacion,
        Optional<Float> calificacion,
        Optional<Float> precioPorNoche,
        Optional<String> proveedorCorreo,
        Optional<String> foto,
        Optional<String> descripcion,
        Optional<Float> latitud,
        Optional<Float> longitud
) {
}
