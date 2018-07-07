package com.softserve.mosquito.services.impl;

import com.mongodb.BasicDBObject;
import com.softserve.mosquito.entities.mongo.TaskMongo;
import com.softserve.mosquito.entities.mongo.TasksBoard;
import com.softserve.mosquito.repo.api.TasksBoardRepo;
import com.softserve.mosquito.services.api.TasksBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TasksBoardServiceImpl implements TasksBoardService {

    private TasksBoardRepo tasksBoardRepo;
    private MongoOperations mongoOperations;

    @Autowired
    public TasksBoardServiceImpl(TasksBoardRepo tasksBoardRepo, MongoOperations mongoOperations) {
        this.tasksBoardRepo = tasksBoardRepo;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<TaskMongo> getUserWork(Long userId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByWorkerId(userId);
        return tasksBoard.getTaskMongos();
    }

    @Override
    public List<TaskMongo> getUserTask(Long ownerId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByOwnerId(ownerId);
        return tasksBoard.getTaskMongos();
    }

    @Override
    public void update(TaskMongo taskMongo, Long workerId) {
        Query query = new Query(Criteria.where("taskMongos.taskId").is(taskMongo.getTaskId()));
        mongoOperations.updateFirst(query,
                new Update().set("taskMongos.$.taskName", taskMongo.getTaskName()), TasksBoard.class);
    }

    @Override
    public void add(TaskMongo taskMongo, Long ownerId, Long workerId) {
        TasksBoard tasksBoard = tasksBoardRepo.findByWorkerId(workerId);
        if (tasksBoard != null) {
            tasksBoard.getTaskMongos().add(taskMongo);
            tasksBoard.setOwnerId(ownerId);
        } else
            tasksBoard = new TasksBoard(workerId, ownerId, Arrays.asList(taskMongo));
        mongoOperations.save(tasksBoard);
    }

    @Override
    public void delete(Long id) {
        Query query = new Query(
                Criteria.where("taskMongos.taskId").is(id)
        );
        mongoOperations.updateFirst(query, new Update().pull("taskMongos", new BasicDBObject("taskId", id)), TasksBoard.class);
    }

}
