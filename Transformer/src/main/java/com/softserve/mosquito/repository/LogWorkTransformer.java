package com.softserve.mosquito.repository;

import com.softserve.mosquito.api.Transformer;
import com.softserve.mosquito.dtos.LogWorkCreateDto;
import com.softserve.mosquito.entities.LogWork;

public class LogWorkTransformer {

    static class LogWorkCreate implements Transformer<LogWork,LogWorkCreateDto>{

        @Override
        public LogWork toEntity(LogWorkCreateDto logWorkCreateDto) {
            return new LogWork(logWorkCreateDto.getDescription(),logWorkCreateDto.getLogged(),logWorkCreateDto.getUserId(),logWorkCreateDto.getEstimationId());
        }

        @Override
        public LogWorkCreateDto toDTO(LogWork logWork) {
            return new LogWorkCreateDto(logWork.getDescription(),logWork.getUserId(),logWork.getLogged(),logWork.getEstimationId());
        }
    }
}
