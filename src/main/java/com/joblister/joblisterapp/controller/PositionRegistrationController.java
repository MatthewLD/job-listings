package com.joblister.joblisterapp.controller;

import com.joblister.joblisterapp.entity.Position;
import com.joblister.joblisterapp.service.PositionRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
public class PositionRegistrationController {
    @Autowired
    PositionRegistrationService positionRegistrationService;

    @PostMapping("/position")
    @ResponseStatus(HttpStatus.CREATED)
    public Object registerPosition(@RequestParam(value = "position name") String name,
                                     @RequestParam(value="position location") String location,
                                     @RequestParam(value="API-key") String apiKey) throws MalformedURLException {
        return positionRegistrationService.registerPosition(name,location,apiKey);
    }
}
