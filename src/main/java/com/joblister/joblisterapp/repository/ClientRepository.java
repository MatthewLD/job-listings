package com.joblister.joblisterapp.repository;

import com.joblister.joblisterapp.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client,Long> {
    Client findClientById(Long id);
    Client findClientByApiID(Long apiId);
    Client findClientByEmail(String email);
    List<Client> findAll();
}