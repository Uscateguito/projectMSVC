package com.microservice.service.model.intermedias;

import com.microservice.service.model.AlojamientoModel;
import com.microservice.service.model.ProviderModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Alojamiento_Proveedor")
public class Alojamiento_Proveedor {

    @Id
    private String id;

    private AlojamientoModel alojamiento;
    private ProviderModel proveedor;
}
