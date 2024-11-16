package com.microservice.service.model.inputs.intermedias;

public record Alojamiento_PersonaInput (
        String fechaCheckIn,
        String fechaCheckOut,
        String alojamientoId,
        String clienteCorreo,
        String comentario,
        float calificacion
) {
}
