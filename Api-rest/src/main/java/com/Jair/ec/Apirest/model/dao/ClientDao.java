package com.Jair.ec.Apirest.model.dao;

import com.Jair.ec.Apirest.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientDao extends CrudRepository<Client, Integer> {
}
