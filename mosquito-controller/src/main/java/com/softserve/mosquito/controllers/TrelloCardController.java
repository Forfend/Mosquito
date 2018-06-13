package com.softserve.mosquito.controllers;


import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.services.api.TrelloCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trello")
public class TrelloCardController {

    private TrelloCardService trelloCardService;

    @Autowired
    public TrelloCardController(TrelloCardService trelloCardService) {
        this.trelloCardService = trelloCardService;
    }

    //not finished
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllUsers() {
        return null;// ResponseEntity.ok().body(trelloCardService);
    }
}
