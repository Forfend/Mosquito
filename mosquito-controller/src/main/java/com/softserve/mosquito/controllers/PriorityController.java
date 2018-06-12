package com.softserve.mosquito.controllers;


import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.services.api.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priorities")

public class PriorityController {
    private PriorityService priorityService;

    @Autowired
    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @PostMapping
    public ResponseEntity<PriorityDto> createPriority(@RequestBody PriorityCreateDto priorityCreateDto){
        PriorityDto createdPriorityDto = priorityService.save(priorityCreateDto);

        if(createdPriorityDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(createdPriorityDto);
    }

    @GetMapping("/{priority_id}")
    public ResponseEntity<PriorityDto> getPriorityById(@PathVariable("priority_id") Long id){
        PriorityDto priority = priorityService.getById(id);

        if(priority == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(priority);
        }
    }


    @PutMapping(path = "/{priority_id}")
    public ResponseEntity<PriorityDto> updatePriority(@PathVariable("priority_id") Long id, @RequestBody PriorityCreateDto priorityCreateDto){
        PriorityDto priorityDto = new PriorityDto(id, priorityCreateDto.getTitle());
        PriorityDto updatetedPriotityDto = priorityService.update(priorityDto);

        if(updatetedPriotityDto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(updatetedPriotityDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PriorityDto> deletePriority(@PathVariable("id") Long id) {
        priorityService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<PriorityDto>> getAllPriority(){
        List<PriorityDto>priorities = priorityService.getAll();

        if(priorities == null || priorities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(priorities);
    }
}