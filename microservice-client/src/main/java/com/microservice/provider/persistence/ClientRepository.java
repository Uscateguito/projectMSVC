package com.microservice.provider.persistence;

import com.microservice.provider.entities.ClientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<ClientModel, String> {

    ClientModel findByCorreo(String correo);
    boolean deleteByCorreo(String correo);
}
