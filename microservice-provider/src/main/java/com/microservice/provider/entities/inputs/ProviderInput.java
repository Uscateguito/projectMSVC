package com.microservice.provider.entities.inputs;

public record ProviderInput(
        String nombre,
        String correo,
        int edad,
        String foto,
        String descripcion,
        String telefono,
        String pagWeb,
        String contactoRedes
) {

}
