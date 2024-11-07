package com.microservice.provider.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Proveedor")
public class ProviderModel {

    @Id
    private String id;

    private String nombre;
    private String correo;
    private int edad;
    private String foto;
    private String descripcion;
    private String telefono;
    private String pagWeb;
    private String contactoRedes;
}
