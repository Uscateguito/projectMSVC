package com.microservice.service.model.intermedias;

import com.microservice.service.model.ProviderModel;
import com.microservice.service.model.TransportModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("Transporte_Proveedor")
public class Transporte_Proveedor {

    @Id
    private String id;

    private ProviderModel proveedor;
    private TransportModel transporte;
}
