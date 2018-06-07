package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.services.api.*;
//import com.softserve.mosquito.services.impl.UserServiceImpl;
import com.softserve.mosquito.transformer.api.TaskTransformer;
import com.softserve.mosquito.dtos.TaskDto;
import com.softserve.mosquito.entities.Task;
//import org.springframework.beans.factory.annotation.Autowired;

public class TaskTransformerImpl implements TaskTransformer {
    /*Using this code provokes cyclic dependencies between modules:
        mosquito-transformer and mosquito-service-impl
    private UserServiceImpl userService;

    private TaskServiceUsingEntity taskServiceUsingEntity;
    private EstimationService estimationService;
    private PriorityService priorityService;
    private StatusService statusService;
    private CommentService commentService;

    @Autowired
    public TaskTransformerImpl(UserServiceImpl userService, TaskServiceUsingEntity taskServiceUsingEntity,
                               EstimationService estimationService, PriorityService priorityService,
                               StatusService statusService, CommentService commentService) {
        this.userService = userService;
        this.taskServiceUsingEntity = taskServiceUsingEntity;
        this.estimationService = estimationService;
        this.priorityService = priorityService;
        this.statusService = statusService;
        this.commentService = commentService;
    }
    */
    @Override
    public Task toEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());

        /* Using this code provokes cyclic dependencies between modules:
        mosquito-transformer and mosquito-service-impl
        task.setParentTask(taskServiceUsingEntity.read(taskDto.getParentId()));

        task.setOwner(userService.getUserById(taskDto.getOwnerId()));
        task.setWorker(userService.getUserById(taskDto.getWorkerId()));

        task.setEstimation(estimationService.getEstimationById(taskDto.getEstimationId()));
        */


        /* There is no method in PriorityService which returns object of class Priority
        Long id = taskDto.getPriorityId();
        Priority priority = priorityService.getPriorityById(id);
        task.setPriority();
        */

        /* There is no method in StatusService which returns object of class Priority
        task.setStatus(statusService.getStatusById(taskDto.getStatusId()));
        */

        /*
        task.setComments(commentService.getAllComments());
        task.setChildTasks(taskServiceUsingEntity.readAll());
        */

        return task;
    }


//            task.setEstimation(new Estimation(taskCreateDto.getEstimation()));
//            task.setOwnerId(taskCreateDto.getOwnerId());
//            task.setWorkerId(taskCreateDto.getWorkerId());
//            task.setParentId(taskCreateDto.getParentId());
//            task.setStatus(new Status(taskCreateDto.getStatusId()));
//            task.setPriority(new Priority(taskCreateDto.getPriorityId()));
//            return task;*/
//        }


    @Override
    public TaskDto toDTO(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setParentId(task.getParentTask().getId());

        taskDto.setOwnerId(task.getOwner().getId());
        taskDto.setFirstNameOfOwner(task.getOwner().getFirstName());
        taskDto.setLastNameOfOwner(task.getOwner().getLastName());

        taskDto.setWorkerId(task.getWorker().getId());
        taskDto.setFirstNameOfWorker(task.getWorker().getFirstName());
        taskDto.setLastNameOfOwner(task.getWorker().getLastName());

        taskDto.setEstimation(task.getEstimation().getTimeEstimation());
        taskDto.setRemaining(task.getEstimation().getRemaining());

        taskDto.setPriorityId(Long.valueOf(task.getPriority().getId()));
        taskDto.setPriorityTitle(task.getPriority().getTitle());

        taskDto.setStatusId(Long.valueOf(task.getStatus().getId()));
        taskDto.setStatusTitle(task.getStatus().getTitle());

        return taskDto;
    }
}
