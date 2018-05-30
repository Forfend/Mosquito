package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.StatusDto;
import com.softserve.mosquito.dtos.StatusCreateDto;

import java.util.List;

public interface StatusService {
    List<StatusDto> getAllStatus();

    StatusDto getStatusById(Long id);

    StatusDto createStatus(StatusCreateDto statusCreateDto);

    StatusDto updateStatus(StatusDto statusDto);

    void removeStatus(StatusDto statusForDelete);
}
