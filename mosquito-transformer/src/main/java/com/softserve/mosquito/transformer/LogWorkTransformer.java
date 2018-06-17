package com.softserve.mosquito.transformer;


import com.softserve.mosquito.dtos.LogWorkDto;
import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.entities.User;

import java.util.ArrayList;
import java.util.List;

public class LogWorkTransformer {

    private LogWorkTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static LogWork toEntity(LogWorkDto logWorkDto) {
        User user = new User();
        user.setId(logWorkDto.getUserId());
        return new LogWork(logWorkDto.getId(), logWorkDto.getDescription(), logWorkDto.getLogged(),
                user, logWorkDto.getEstimationId(), logWorkDto.getLastUpdate());
    }

    public static LogWorkDto toDTO(LogWork logWork) {
        return new LogWorkDto(logWork.getId(), logWork.getDescription(), logWork.getAuthor().getId(),
                logWork.getLogged(), logWork.getLastUpdate(), logWork.getEstimation().getId());
    }


    public static List<LogWork> toEntityList(List<LogWorkDto> logWorksDtos) {
        List<LogWork> logWorks = new ArrayList<>();
        if(logWorksDtos != null) {
            for (LogWorkDto logWorkDto : logWorksDtos) {
                logWorks.add(toEntity(logWorkDto));
            }
        }
        return logWorks;
    }

    public static List<LogWorkDto> toDTOList(List<LogWork> logWorks) {
        List<LogWorkDto> logWorkDtos = new ArrayList<>();
        if(logWorks != null) {
            for (LogWork logWork : logWorks) {
                logWorkDtos.add(toDTO(logWork));
            }
        }
        return logWorkDtos;
    }

}

