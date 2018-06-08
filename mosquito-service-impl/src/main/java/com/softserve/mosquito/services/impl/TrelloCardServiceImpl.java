package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TaskDto;

import com.softserve.mosquito.entities.*;
import org.codehaus.jackson.map.ObjectMapper;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import javax.ws.rs.core.Response;

@Service
public class TrelloCardServiceImpl {

    // MEGA KOSTUL********************************
    private Long userId = 44L;
    private TrelloInfoServiceImpl trelloInfoService;
    private TrelloInfo trelloInfo = trelloInfoService.getTrelloInfoByUserId(userId);
    //*********************************************

    @Autowired
    public TrelloCardServiceImpl(TrelloInfoServiceImpl trelloInfoService) {
        this.trelloInfoService = trelloInfoService;
    }

    @Transactional
    public void getTasksFromTrello(){

        for (TrelloBoard trelloBoard : getAllTrelloBoards()){
            for (TrelloList trelloList : getTrelloListsByBoard(trelloBoard.getId())){
                if (trelloList.getName().equalsIgnoreCase("todo")){
                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "todo", trelloBoard.getName());
                }
                if (trelloList.getName().equalsIgnoreCase("doing")){
                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "doing", trelloBoard.getName());
                }
                if (trelloList.getName().equalsIgnoreCase("done")){
                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "done", trelloBoard.getName());
                }
            }

        }
    }

   private void createTasksFromTrelloCards(TrelloCard[] trelloCards, String status, String projectName){

//        TaskServiceUsingEntityImpl taskService = new TaskServiceImpl();
//        TaskDto taskDto = new TaskDto();
//        taskDto.setName(projectName);
//        taskDto.setOwnerId(trelloInfo.getUserId());
//        taskDto.setWorkerId(trelloInfo.getUserId());
//        Task task = taskService.create(taskDto);
//
//        for (TrelloCard trelloCard : trelloCards){
//            TaskDto trelloTask = new TaskDto();
//            trelloTask.setName(trelloCard.getName());
//            trelloTask.setWorkerId(trelloInfo.getUserId());
//            trelloTask.setOwnerId(trelloInfo.getUserId());
//            trelloTask.setParentId(task.getId());
//            trelloTask.setStatusId(new StatusServiceImpl().getStatusByName(status).getId());
//            taskService.create(trelloTask);
//        }
   }

    private TrelloBoard[] getAllTrelloBoards(){
        TrelloBoard[] trelloBoards= null;
        String urlGetAllBoards = String.format("https://impl.trello.com/1/members/%s/boards?key=%s&token=%s",
                trelloInfo.getUserTrelloName(), trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());

        try {
            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(urlGetAllBoards);

            Response response = target.request().get();

            String responseAsString = response.readEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();

            trelloBoards = mapper.readValue(responseAsString, TrelloBoard[].class);

        } catch (Exception e) {
            System.err.println(e);
        }

        return trelloBoards;
    }

    private TrelloList[] getTrelloListsByBoard(String idBoard){
        TrelloList[] TrelloLists = null;
        String urlGetListOfBoard = String.format("https://impl.trello.com/1/boards/%s/lists?cards=open&card_fields=name&fields=name&key=%s&token=%s",
                idBoard, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());

        try {

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(urlGetListOfBoard);

            Response response = target.request().get();

            String responseAsString = response.readEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();

            TrelloLists = mapper.readValue(responseAsString, TrelloList[].class);

        } catch (Exception e) {
            System.err.println(e);
        }

        return TrelloLists;
    }

    private TrelloCard[] getTrelloCardsByList(String idList){
        TrelloCard[] TrelloCards = null;

        String urlGetCardsByList= String.format("https://impl.trello.com/1/lists/%s/cards?key=%s&token=%s",
                idList, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());

        try {

            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target(urlGetCardsByList);

            Response response = target.request().get();

            String responseAsString = response.readEntity(String.class);

            ObjectMapper mapper = new ObjectMapper();

            TrelloCards = mapper.readValue(responseAsString, TrelloCard[].class);

        } catch (Exception e) {
            System.err.println(e);
        }

        return TrelloCards;
    }

}
