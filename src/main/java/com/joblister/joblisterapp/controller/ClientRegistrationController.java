package com.joblister.joblisterapp.controller;

import com.joblister.joblisterapp.service.ClientRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ClientRegistrationController {
    @Autowired
    ClientRegistrationService clientRegistrationService;

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public Object registerClient(@RequestParam(value = "name") String name,
                                 @RequestParam(value = "email") String email) {
        return clientRegistrationService.registerClientInDB(name,email);
    }
}
