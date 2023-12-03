package com.Jair.ec.Apirest.controller;

import com.Jair.ec.Apirest.model.dto.ClientDto;
import com.Jair.ec.Apirest.model.entity.Client;
import com.Jair.ec.Apirest.model.payload.MessageResponse;
import com.Jair.ec.Apirest.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping("clients")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?>  showAll(){
        List<Client> getList = clientService.listAll();
        if(getList == null)
        {
            return new ResponseEntity<>(MessageResponse.builder().message("No hay registros")
                    .object(null), HttpStatus.OK);
        }

        return new ResponseEntity<>(MessageResponse.builder().message("")
                .object(getList).build(), HttpStatus.OK);
    }
    @PostMapping("client")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto){
        Client clientSave = null;
        try {
            clientSave = clientService.save(clientDto);
            clientDto.builder().
                    id(clientSave.getId())
                    .name(clientSave.getName())
                    .lastname(clientSave.getLastname())
                    .registerDate(clientSave.getRegisterDate())
                    .email(clientSave.getEmail())
                    .build();
            return new ResponseEntity<>(MessageResponse.builder().message("Client created successfully")
                    .object(clientDto).build(), HttpStatus.CREATED);
        } catch (DataAccessException dtEx) {
            return new ResponseEntity<>(MessageResponse.builder().message(dtEx.getMessage())
                    .object(null), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("client/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody ClientDto clientDto, @PathVariable Integer id){
        Client clientUpdate = null;
        try {

            if(clientService.existsById(id))
            {
                clientDto.setId(id);
                clientUpdate = clientService.save(clientDto);
                clientDto.builder().
                        id(clientUpdate.getId())
                        .name(clientUpdate.getName())
                        .lastname(clientUpdate.getLastname())
                        .registerDate(clientUpdate.getRegisterDate())
                        .email(clientUpdate.getEmail())
                        .build();
                return new ResponseEntity<>(MessageResponse.builder().message("Client created successfully")
                        .object(clientDto).build(), HttpStatus.CREATED);
            }else
            {
                return new ResponseEntity<>(MessageResponse.builder().message("Client not found")
                        .object(clientDto).build(), HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException dtEx) {
            return new ResponseEntity<>(MessageResponse.builder().message(dtEx.getMessage())
                    .object(null), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Client client = clientService.findById(id);
            clientService.delete(client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (DataAccessException dtEx) {
            return new ResponseEntity<>(MessageResponse.builder().message(dtEx.getMessage())
                    .object(null), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?>  showById(@PathVariable Integer id){
        Client client = clientService.findById(id);
        if(client == null)
        {
            return new ResponseEntity<>(MessageResponse.builder().message("Client not found")
                    .object(null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(MessageResponse.builder().message("Client with id: "+id+" found successfully")
                .object(ClientDto.builder().
                        id(client.getId())
                        .name(client.getName())
                        .lastname(client.getLastname())
                        .registerDate(client.getRegisterDate())
                        .email(client.getEmail())
                        .build()).build(), HttpStatus.OK);
    }
}
