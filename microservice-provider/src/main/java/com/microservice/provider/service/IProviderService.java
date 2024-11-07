package com.microservice.provider.service;

import com.microservice.provider.entities.ProviderModel;

import java.util.List;

public interface IProviderService {

    List<ProviderModel> findAll();
    ProviderModel findById(String id);
    void save(ProviderModel student);
}
