package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.PriorityCreateDto;
import com.softserve.mosquito.dtos.PriorityDto;
import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.repo.api.PriorityRepo;
import com.softserve.mosquito.services.api.PriorityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PriorityServiceImpl implements PriorityService {

    private PriorityRepo priorityRepo;
    //TODO: Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PriorityServiceImpl(PriorityRepo priorityRepo) {
        this.priorityRepo = priorityRepo;
    }

    @Transactional
    @Override
    public List<PriorityDto> getAll(){
        List<Priority> priorities = priorityRepo.getAll();

        if(priorities == null || priorities.isEmpty()){
            return null;
        }

        List<PriorityDto> priorityDtos = new ArrayList<>();
        for(Priority priority: priorities){
            priorityDtos.add(modelMapper.map(priority, PriorityDto.class));
        }

        return priorityDtos;
    }

    @Transactional
    @Override
    public PriorityDto getById(Long id){

        Priority priority = priorityRepo.read(id);

        if(priority == null){
            return null;
        }

        return modelMapper.map(priority, PriorityDto.class);
    }

    @Transactional
    @Override
    public PriorityDto save(PriorityCreateDto priorityCreateDto){
        Priority createdPriority = priorityRepo.create(modelMapper.map(priorityCreateDto, Priority.class));

        if(createdPriority == null){
            return null;
        }

        return modelMapper.map(createdPriority, PriorityDto.class);
    }

    @Transactional
    @Override
    public PriorityDto update(PriorityDto priorityDto){
        Priority updatedPriority = priorityRepo.update(modelMapper.map(priorityDto, Priority.class));

        if(updatedPriority == null){
            return null;
        }

        return modelMapper.map(updatedPriority, PriorityDto.class);
    }

    @Transactional
    @Override
    public void delete(Long id){
        priorityRepo.delete(id);
    }

    @Override
    public Priority getPriorityEntityById(Long id) {
        Priority priority = priorityRepo.read(id);

        if(priority == null){
            return null;
        }

        return priority;
    }
}
