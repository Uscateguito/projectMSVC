package com.microservice.service.repository;

import com.microservice.service.model.intermedias.Alojamiento_Persona;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Alojamiento_PersonaRepository extends MongoRepository<Alojamiento_Persona, String> {

    List<Alojamiento_Persona> findAllByCliente_Correo(String correo);
    int countAllByCliente_Correo(String correo);
}
