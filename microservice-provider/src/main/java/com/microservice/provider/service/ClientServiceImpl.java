package com.microservice.provider.service;

import com.microservice.provider.entities.ProviderModel;
import com.microservice.provider.persistence.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<ProviderModel> findAll() {
        return (List<ProviderModel>) providerRepository.findAll();
    }

    @Override
    public ProviderModel findById(String id) {
        return providerRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(ProviderModel provider) {
        providerRepository.save(provider);
    }

//    @Override
//    public List<ClientModel> findByCourseId(Long courseId) {
//        return clientRepository.findAll(courseId);
//    }
}
