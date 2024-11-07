package com.microservice.service.repository;


import com.microservice.service.model.intermedias.Transporte_Proveedor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Transporte_ProveedorRepository extends MongoRepository<Transporte_Proveedor, String> {

        List<Transporte_Proveedor> findAllByProveedor_Correo(String correo);
        int countAllByProveedor_Correo(String correo);
}
