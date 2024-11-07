package com.microservice.service.repository;

import com.microservice.service.model.ProviderModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProveedorRepository extends MongoRepository<ProviderModel, String> {
    ProviderModel findByCorreo(String correo);
    boolean deleteByCorreo(String correo);
}
