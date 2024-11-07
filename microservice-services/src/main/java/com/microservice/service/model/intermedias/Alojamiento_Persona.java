package com.microservice.service.model.intermedias;

import com.microservice.service.model.AlojamientoModel;
import com.microservice.service.model.ClientModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Alojamiento_Persona")
public class Alojamiento_Persona {

    @Id
    private String id;

    private String fechaCheckIn;
    private String fechaCheckOut;

    private AlojamientoModel alojamiento;
    private ClientModel cliente;
}
