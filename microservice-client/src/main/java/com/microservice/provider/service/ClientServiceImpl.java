package com.microservice.provider.service;

import com.microservice.provider.entities.ClientModel;
import com.microservice.provider.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientModel> findAll() {
        return (List<ClientModel>) clientRepository.findAll();
    }

    @Override
    public ClientModel findById(String id) {
        return clientRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(ClientModel client) {
        clientRepository.save(client);
    }

//    @Override
//    public List<ClientModel> findByCourseId(Long courseId) {
//        return clientRepository.findAll(courseId);
//    }
}
