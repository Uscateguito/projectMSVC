package com.microservice.service.repository;

import com.microservice.service.model.AlojamientoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AlojamientoRepository extends MongoRepository<AlojamientoModel, String> {

    List<AlojamientoModel> findAllByProveedor_Correo(String correo);
}
