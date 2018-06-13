package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;

import java.util.ArrayList;
import java.util.List;

public class PriorityTransformer {

    public static Priority toEntity(PriorityDto priorityDto) {
        return new Priority(priorityDto.getId(), priorityDto.getTitle());
    }

    public static PriorityDto toDTO(Priority priority) {
        return new PriorityDto(priority.getId(), priority.getTitle());
    }

    public static List<Priority> toEntityList(List<PriorityDto> priorityDtos) {
        List<Priority> priorities = new ArrayList<>();

        for(PriorityDto priorityDto: priorityDtos){
            priorities.add(toEntity(priorityDto));
        }
        return priorities;
    }

    public static List<PriorityDto> toDTOList(List<Priority> priorities){
        List<PriorityDto> priorityDtos = new ArrayList<>();

        for(Priority priority: priorities){
            priorityDtos.add(toDTO(priority));
        }
        return priorityDtos;
    }

}