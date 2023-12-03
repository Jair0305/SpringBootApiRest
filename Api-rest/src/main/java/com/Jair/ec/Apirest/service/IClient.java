package com.Jair.ec.Apirest.service;

import com.Jair.ec.Apirest.model.entity.Client;

public interface IClient {

    Client save(Client client);

    Client findById(Integer id);

    void delete(Client client);



}
