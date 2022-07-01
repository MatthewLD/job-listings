package com.joblister.joblisterapp.controller;

import com.joblister.joblisterapp.entity.Position;
import com.joblister.joblisterapp.service.PositionSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/position")
public class PositionSearchController {
    @Autowired
    PositionSearchService positionSearchService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Object searchPositions(@RequestParam(name = "Key word") String keyWord,
                                  @RequestParam(name = "Location", required = false, defaultValue = "") String location,
                                  @RequestParam(name = "API-key") String apiKey) {
        return positionSearchService.searchPosition(keyWord, location, apiKey);
    }

    @GetMapping("/search/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Position searchPositionById(@RequestParam(name = "id")Long id) {
        return positionSearchService.findById(id);
    }
}