package com.joblister.joblisterapp.controller;

import com.joblister.joblisterapp.entity.Position;
import com.joblister.joblisterapp.service.PositionSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionSearchController {
    @Autowired
    PositionSearchService positionSearchService;

    @GetMapping("/search")
    public Object searchPositions(@RequestParam(name = "Key word") String keyWord, @RequestParam(name = "Location") String location, @RequestParam(name = "API-key") String apiKey) {
        return positionSearchService.searchPosition(keyWord, location, apiKey);
    }

    @GetMapping("/search/{id}")
    public Position searchPositionById(@RequestParam(name = "id")Long id) {
        return positionSearchService.findById(id);
    };
}