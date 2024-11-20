package com.microservice.service.repository;

import com.microservice.service.model.intermedias.Transporte_Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Arrays;
import java.util.List;

public interface Transporte_PersonaRepository extends MongoRepository<Transporte_Persona, String> {

    List<Transporte_Persona> findAllByCliente_Correo(String correo);
    int countAllByCliente_Correo(String correo);

    Transporte_Persona findByTransporte_IdAndCliente_Correo(String id, String correo);

    List<Transporte_Persona> findAllByTransporte_Id(String transporteId);

    Float countAllByTransporte_Id(String transporteId);
}
