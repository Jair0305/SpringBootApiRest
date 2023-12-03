package com.Jair.ec.Apirest.service.impl;

import com.Jair.ec.Apirest.model.dao.ClientDao;
import com.Jair.ec.Apirest.model.entity.Client;
import com.Jair.ec.Apirest.service.IClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientImpl implements IClient {

    @Autowired
    private ClientDao clientDao;

    @Transactional
    @Override
    public Client save(Client client) {
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
}
