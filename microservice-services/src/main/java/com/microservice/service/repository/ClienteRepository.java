package com.microservice.service.repository;

import com.microservice.service.model.ClientModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<ClientModel, String> {

    ClientModel findByCorreo(String correo);
    boolean deleteByCorreo(String correo);
}
