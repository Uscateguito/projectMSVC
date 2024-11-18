package com.microservice.service.repository;


import com.microservice.service.model.TransportModel;
import com.microservice.service.model.intermedias.Transporte_Proveedor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransporteRepository extends MongoRepository<TransportModel, String> {

    List<TransportModel> findAllByProveedor_Correo(String proveedorCorreo);
}
