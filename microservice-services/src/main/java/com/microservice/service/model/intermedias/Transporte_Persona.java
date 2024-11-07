package com.microservice.service.model.intermedias;

import com.microservice.service.model.ClientModel;
import com.microservice.service.model.TransportModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Transporte_Persona")
public class Transporte_Persona {

    @Id
    private String id;
    private String numeroPlaca;

    private ClientModel cliente;
    private TransportModel transporte;
}
