package com.microservice.provider.entities.inputs;

public record ClienteInput(
        String nombre,
        String correo,
        int edad,
        String foto,
        String descripcion
) {
}
