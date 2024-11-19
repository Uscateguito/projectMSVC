package com.microservice.service.model.inputs.intermedias;

import java.util.Optional;

public record Alojamiento_PersonaInput (
        Optional<String> fechaCheckIn,
        Optional<String> fechaCheckOut,
        Optional<String> alojamientoId,
        Optional<String> clienteCorreo,
        Optional<String> comentario,
        Optional<Float> calificacion
) {
}
