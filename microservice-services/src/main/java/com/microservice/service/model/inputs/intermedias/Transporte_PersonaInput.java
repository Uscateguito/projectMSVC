package com.microservice.service.model.inputs.intermedias;

import java.util.Optional;

public record Transporte_PersonaInput(
        Optional<String> numeroPlaca,
        Optional<String> clienteCorreo,
        Optional<String> transporteId,
        Optional<String> comentario,
        Optional<Float> calificacion
) {
}
