package com.microservice.service.model;

import com.microservice.service.model.patrones.Servicio;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Alojamiento")
public class AlojamientoModel implements Servicio {
    
    @Id
    private String id;

    private String nombre;
    private String foto;
    private float calificacion;
    private String ubicacion;
    private double precioPorNoche;
    private String descripcion;
    private float latitud;
    private float longitud;

    @DBRef
    private ProviderModel proveedor;

}
