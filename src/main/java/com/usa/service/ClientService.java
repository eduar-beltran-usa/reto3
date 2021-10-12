package com.usa.service;

import com.usa.entities.Client;
import com.usa.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    
    
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClient(int id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        if (client.getIdClient()== null) {
            return clientRepository.save(client);
        } else {
            Optional<Client> mot = clientRepository.findById(client.getIdClient());
            if (mot.isEmpty()) {
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }

    public Client update(Client client) {
        if (client.getIdClient()!= null) {
            Optional<Client> clien = clientRepository.findById(client.getIdClient());
            if (!clien.isEmpty()) {
                if (client.getName() != null) {
                    clien.get().setName(client.getName());
                }
                if (client.getEmail() != null) {
                    clien.get().setEmail(client.getEmail());
                }
                if (client.getAge()!= null) {
                    clien.get().setAge(client.getAge());
                }
                if (client.getPassword()!= null) {
                    clien.get().setPassword(client.getPassword());
                }
                clientRepository.save(clien.get());
                return clien.get();
            } else {
                return client;
            }
        } else {
            return client;
        }

    }

    public boolean deleteClient(int id) {
        Boolean clientBoolean = clientRepository.findById(id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return clientBoolean;
    }
    
}
