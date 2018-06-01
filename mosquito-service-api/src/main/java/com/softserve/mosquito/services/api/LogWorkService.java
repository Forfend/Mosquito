package com.softserve.mosquito.services.api;

import com.softserve.mosquito.entities.LogWork;

import java.util.List;

public interface LogWorkService {

    LogWork createLogWork(LogWork logWorkId);

    LogWork getLogWorkById(Long logWorkId);

    LogWork updateLogWork(LogWork logWork);

    void removeLogWork(Long id);

    List<LogWork> getAllLogWork();

    List<LogWork> getLogWorksByEstimation(Long estimationId);
}
