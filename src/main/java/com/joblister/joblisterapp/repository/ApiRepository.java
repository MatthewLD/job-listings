package com.joblister.joblisterapp.repository;

import com.joblister.joblisterapp.entity.Api;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ApiRepository extends CrudRepository<Api,Long> {
    Api findAPIByKeyvalue(UUID keyvalue);
    Api findAPIById(Long id);
}
