package com.microservice.provider.persistence;

import com.microservice.provider.entities.ProviderModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends MongoRepository<ProviderModel, String> {

    ProviderModel findByCorreo(String correo);
    boolean deleteByCorreo(String correo);
}
