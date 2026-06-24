package com.tristan.clients.services;

import com.tristan.clients.dtos.ClientDto;
import com.tristan.clients.models.Client;
import com.tristan.clients.repositories.ClientRepository;
import com.tristan.clients.requests.RegisterClientRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll(String sort) {
        return clientRepository.findAll(Sort.by(sort));
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).get();
    }

    public Client update(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
