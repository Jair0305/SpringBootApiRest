package com.Jair.ec.Apirest.service;

import com.Jair.ec.Apirest.model.dto.ClientDto;
import com.Jair.ec.Apirest.model.entity.Client;

import java.util.List;

public interface IClientService {

    List<Client> listAll();

    Client save(ClientDto client);

    Client findById(Integer id);

    void delete(Client client);

    boolean existsById(Integer id);

}
