package com.microservice.provider.service;

import com.microservice.provider.entities.ClientModel;

import java.util.List;

public interface IClientService {

    List<ClientModel> findAll();
    ClientModel findById(String id);
    void save(ClientModel student);
}
