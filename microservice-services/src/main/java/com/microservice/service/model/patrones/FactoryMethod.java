package com.microservice.service.model.patrones;

import com.microservice.service.model.AlojamientoModel;
import com.microservice.service.model.TransportModel;

public class FactoryMethod {

    public static Servicio crearServicio(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "transporte" -> new TransportModel();
            case "alojamiento" -> new AlojamientoModel();
            default -> throw new IllegalArgumentException("Tipo de servicio no reconocido");
        };
    }
}
