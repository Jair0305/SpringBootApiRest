package com.Jair.ec.Apirest.service.impl;

import com.Jair.ec.Apirest.model.dao.ClientDao;
import com.Jair.ec.Apirest.model.dto.ClientDto;
import com.Jair.ec.Apirest.model.entity.Client;
import com.Jair.ec.Apirest.service.IClientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientImplService implements IClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public List<Client> listAll() {
        return (List) clientDao.findAll();
    }

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        Client client = Client.builder().
                id(clientDto.getId())
                .name(clientDto.getName())
                .lastname(clientDto.getLastname())
                .registerDate(clientDto.getRegisterDate())
                .email(clientDto.getEmail())
                .build();
        return clientDao.save(client);
    }

    @Override
    public Client findById(Integer id) {
        return clientDao.findById(id).orElse(null);
    }
    @Transactional
    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Override
    public boolean existsById(Integer id) {
        return clientDao.existsById(id);
    }
}
