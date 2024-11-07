package com.microservice.service.repository;

import com.microservice.service.model.AlojamientoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlojamientoRepository extends MongoRepository<AlojamientoModel, String> {

}
