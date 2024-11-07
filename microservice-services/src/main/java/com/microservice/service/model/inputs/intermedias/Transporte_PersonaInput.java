package com.microservice.service.model.inputs.intermedias;

public record Transporte_PersonaInput(
        String numeroPlaca,
        String clienteCorreo,
        String transporteId
) {
}
