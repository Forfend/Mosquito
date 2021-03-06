package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.TaskFullDto;
import com.softserve.mosquito.dtos.TaskSimpleDto;
import com.softserve.mosquito.entities.mongo.TaskMongo;
import com.softserve.mosquito.services.api.TaskService;
import com.softserve.mosquito.services.api.TasksBoardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
@Api(value = "Task controller", description = "Controller for doing CRUD operation with task")
public class TaskController {

    private TaskService taskService;
    private TasksBoardService tasksBoardService;

    @Autowired
    public TaskController(TaskService taskService, TasksBoardService tasksBoardService) {
        this.taskService = taskService;
        this.tasksBoardService = tasksBoardService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskFullDto createTask(@RequestBody TaskFullDto taskFullDto) {
        return taskService.save(taskFullDto);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskFullDto updateTask(@PathVariable("id") Long id, @RequestBody TaskFullDto taskFullDto) {
        return taskService.update(taskFullDto);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.delete(id);
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskSimpleDto getTaskById(@PathVariable("id") Long id) {
        if (taskService.getSimpleTaskById(id) == null)
            throw new TaskNotFoundException("Task with Id " + id + " not found!");
        return taskService.getSimpleTaskById(id);
    }

    @GetMapping(path = "/{id}/info")
    @ResponseStatus(HttpStatus.OK)
    public TaskFullDto getFullTaskById(@PathVariable("id") Long id) {
        return taskService.getById(id);
    }

    @GetMapping(path = "{id}/sub-tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskFullDto> getSubTasks(@PathVariable("id") Long id) {
        return taskService.getSubTasks(id);
    }

    @GetMapping(path = "/owners-tasks/{owner_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskFullDto> getOwnerTasks(@PathVariable("owner_id") Long ownerId) {
        return taskService.getByOwner(ownerId);
    }

    @GetMapping(path = "workers-tasks/{worker_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskFullDto> getWorkerTasks(@PathVariable("worker_id") Long workerId) {
        return taskService.getByWorker(workerId);
    }

    @GetMapping(path = "/tasks-board")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskMongo> mongoTest(@RequestParam(name = "worker_id") Long workerId) {
        return tasksBoardService.getUserWork(workerId);
    }
}
