package com.softserve.mosquito.transformer;

import com.softserve.mosquito.dtos.TaskFullDto;
import com.softserve.mosquito.dtos.TaskSimpleDto;
import com.softserve.mosquito.entities.Task;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class TaskTransformer {

    private TaskTransformer() {
    }

    //parentTask, comments, and childTasks will set on service-impl module
    public static Task toEntity(TaskFullDto taskFullDto) {
        return Task.builder()
                .id(taskFullDto.getId())
                .name(taskFullDto.getName())
                .owner(UserTransformer.toEntity(taskFullDto.getOwnerDto()))
                .worker(UserTransformer.toEntity(taskFullDto.getWorkerDto()))
                .estimation(EstimationTransformer.toEntity(taskFullDto.getEstimationDto()))
                .priority(PriorityTransformer.toEntity(taskFullDto.getPriorityDto()))
                .status(StatusTransformer.toEntity(taskFullDto.getStatusDto()))
                .build();
    }

    //parentTask, comments, and childTasks will set on service-impl module

    public static TaskFullDto toBigDTO(Task task) {
        return new TaskFullDto().builder()
                .id(task.getId())
                .name(task.getName())
                .ownerDto(UserTransformer.toDTO(task.getOwner()))
                .workerDto(UserTransformer.toDTO(task.getOwner()))
                .estimationDto(EstimationTransformer.toDTO(task.getEstimation()))
                .priorityDto(PriorityTransformer.toDTO(task.getPriority()))
                .statusDto(StatusTransformer.toDTO(task.getStatus()))
                .build();
    }


    //TODO must be more simple than BigDto
    public static TaskFullDto toMediumDTO(Task task) {
        return new TaskFullDto().builder()
                .id(task.getId())
                .name(task.getName())
                .ownerDto(UserTransformer.toDTO(task.getOwner()))
                .workerDto(UserTransformer.toDTO(task.getOwner()))
                .estimationDto(EstimationTransformer.toDTO(task.getEstimation()))
                .priorityDto(PriorityTransformer.toDTO(task.getPriority()))
                .statusDto(StatusTransformer.toDTO(task.getStatus()))
                .build();
    }



    public static List<Task> toEntityList(List<TaskFullDto> taskFullDtoList) {
        List<Task> tasks = new ArrayList<>();
        for (TaskFullDto taskFullDto : taskFullDtoList) {
            tasks.add(toEntity(taskFullDto));
        }
        return tasks;
    }

    public static List<TaskFullDto> toDTOList(List<Task> tasks) {
        List<TaskFullDto> taskFullDtoList = new ArrayList<>();
        for (Task task : tasks) {
            taskFullDtoList.add(toBigDTO(task));
        }
        return taskFullDtoList;
    }

    public static TaskSimpleDto toSimpleDto(Task task){
        TaskSimpleDto taskSimpleDto = new TaskSimpleDto();
        taskSimpleDto.setId(task.getId());
        taskSimpleDto.setName(task.getName());
        taskSimpleDto.setParentTask(task.getParentTask() != null ? task.getParentTask().getName() : null);
        taskSimpleDto.setOwner(task.getOwner().getId().toString());
        taskSimpleDto.setWorker(task.getOwner().getId().toString());
        taskSimpleDto.setEstimation(task.getEstimation().getTimeEstimation().toString());
        taskSimpleDto.setPriority(task.getPriority().getTitle());
        taskSimpleDto.setStatus(task.getStatus().getTitle());
        return taskSimpleDto;
    }
}
