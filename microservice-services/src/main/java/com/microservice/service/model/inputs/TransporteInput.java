package com.microservice.service.model.inputs;

import java.util.Optional;

public record TransporteInput(

        Optional<String> tipo,
        Optional<Integer> capacidad,
        Optional<String> operador,
        Optional<Float> precio,
        Optional<Float> calificacion,
        Optional<String> origen,
        Optional<String> destino,
        Optional<String> fechaSalida,
        Optional<String> horaSalida,
        Optional<Integer> duracionEstimada,
        Optional<String> proveedorCorreo
) {
}
