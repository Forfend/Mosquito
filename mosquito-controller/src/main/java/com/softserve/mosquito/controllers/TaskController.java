package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.CommentDto;
import com.softserve.mosquito.dtos.TaskFullDto;
import com.softserve.mosquito.dtos.TaskSimpleDto;
import com.softserve.mosquito.entities.mongo.Task;
import com.softserve.mosquito.services.api.CommentService;
import com.softserve.mosquito.services.api.TaskService;
import com.softserve.mosquito.services.api.TasksBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TaskController {

    private TaskService taskService;
    private CommentService commentService;
    private TasksBoardService tasksBoardService;


    @Autowired
    public TaskController(TaskService taskService, CommentService commentService, TasksBoardService tasksBoardService) {
        this.taskService = taskService;
        this.commentService = commentService;
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

//    @GetMapping(path = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public TaskFullDto getTaskById(@PathVariable("id") Long id) {
//        return taskService.getById(id);
//    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskSimpleDto getTaskById(@PathVariable("id") Long id) {
        if (taskService.getSimpleTaskById(id) == null)
            throw new TaskNotFoundException("Task with Id " + id + " not found!");
        return taskService.getSimpleTaskById(id);
    }

    @GetMapping(path = "{id}/subtasks")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskFullDto> getSubTasks(@PathVariable("id") Long id) {
        return taskService.getSubTasks(id);
    }

    @GetMapping(path = "/tasks-as-owner/{owner_id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskFullDto getOwnerTasks(@PathVariable("owner_id") Long ownerId) {
        return null;
    }

    @GetMapping(path = "tasks-as-worker/{worker_id}/")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskFullDto> getWorkerTasks(@PathVariable("worker_id") Long workerId) {
        return new ArrayList<>();
    }

    @GetMapping(path = "/task-by-status/{status_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskFullDto> getTaskByStatus(@PathVariable("status_id") Long statusId) {
        return new ArrayList<>();
    }

    @GetMapping(path = "/tasks-board")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> mongoTest(@RequestParam(name = "worker_id") Long workerId) {
        /*tasksBoardService.add(new Task(100L, "task1"), 1L);
        tasksBoardService.add(new Task(101L, "task2"), 1L);
        tasksBoardService.add(new Task(102L, "task3"), 1L);

        tasksBoardService.add(new Task(103L, "task4"), 2L);
        */

        return tasksBoardService.getUserWork(workerId);
    }
}
