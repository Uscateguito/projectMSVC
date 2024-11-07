package com.microservice.provider.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Document("Cliente")
public class ClientModel {

    @Id
    private String id;

    private String nombre;
    private String correo;
    private int edad;
    private String foto;
    private String descripcion;

}
