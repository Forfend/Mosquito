package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.*;
import com.softserve.mosquito.services.api.*;
import org.codehaus.jackson.map.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrelloCardServiceImpl implements TrelloCardService {

    private TrelloInfoService trelloInfoService;
    private TaskService taskService;
    private StatusService statusService;
    private UserService userService;
    private TrelloInfoDto trelloInfo;

    @Autowired
    public TrelloCardServiceImpl(TrelloInfoService trelloInfoService, TaskService taskService,
                                 StatusService statusService, UserService userService) {

        this.trelloInfoService = trelloInfoService;
        this.taskService = taskService;
        this.statusService = statusService;
        this.userService = userService;

    }

//    @Transactional
//    @Override
//    public void getTasksFromTrello() {
//
//        for (TrelloBoardDto trelloBoard : getAllTrelloBoards()) {
//            for (TrelloListDto trelloList : getTrelloListsByBoard(trelloBoard.getId())) {
//                if (trelloList.getName().equalsIgnoreCase("todo")) {
//                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "todo", trelloBoard.getName());
//                }
//                if (trelloList.getName().equalsIgnoreCase("doing")) {
//                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "doing", trelloBoard.getName());
//                }
//                if (trelloList.getName().equalsIgnoreCase("done")) {
//                    createTasksFromTrelloCards(getTrelloCardsByList(trelloList.getId()), "done", trelloBoard.getName());
//                }
//            }
//        }
//    }

    public List<TaskSimpleDto> showAllNewTrelloTasks(Long userId) {

        trelloInfo = trelloInfoService.getByUserId(userId);
        List<TaskSimpleDto> trelloTasks = new ArrayList<TaskSimpleDto>();

        for (TrelloBoardDto trelloBoard : getAllTrelloBoards()) {

            for (TrelloListDto trelloList : getTrelloListsByBoard(trelloBoard.getId())) {

                if (trelloList.getName().equalsIgnoreCase("todo") ||
                        trelloList.getName().equalsIgnoreCase("doing") ||
                        trelloList.getName().equalsIgnoreCase("done")) {

                    UserDto userDto = trelloInfo.getUserDto();
                    TaskSimpleDto taskDto = new TaskSimpleDto(trelloBoard.getName(), userDto.getId().toString(), userDto.getId().toString(),
                            "todo", trelloBoard.getId());

                    if (!taskService.isPresent(taskDto.getTrelloId()) && !taskDto.isPresentInCollection(trelloTasks)) trelloTasks.add(taskDto);

                    trelloTasks.addAll(CollectTaskSimpleDtosFromTrelloCards(getTrelloCardsByList(trelloList.getId()),
                            trelloList.getName().toLowerCase(), trelloBoard.getName(), trelloList));
                }
            }
        }
        return trelloTasks;
    }

    private List<TaskSimpleDto> CollectTaskSimpleDtosFromTrelloCards(TrelloCardDto[] trelloCards, String status, String projectName, TrelloListDto trelloList) {

        List<TaskSimpleDto> trelloTasks = new ArrayList<TaskSimpleDto>();
        UserDto userDto = trelloInfo.getUserDto();

        for (TrelloCardDto trelloCard : trelloCards) {

            TaskSimpleDto trelloTask = new TaskSimpleDto(trelloCard.getName(), projectName, userDto.getId().toString(), userDto.getId().toString(),
                    status, trelloCard.getId());

            if (!taskService.isPresent(trelloCard.getId())) trelloTasks.add(trelloTask);
        }
        return trelloTasks;
    }

    // 2 methods create from trello and fron json file
    /*public void createTastsFromTrello(Long userId){
        List<TaskSimpleDto> simpleDtos = showAllNewTrelloTasks(userId);
        //not finished

    }*/

//    private void createTasksFromTrelloCards(TrelloCardDto[] trelloCards, String status, String projectName) {
//
//        TaskFullDto taskFullDto = new TaskFullDto();
//        taskFullDto.setName(projectName);
//        taskFullDto.setOwnerDto(userService.getById(trelloInfo.getUserDto().getId()));
//        taskFullDto.setWorkerDto(userService.getById(trelloInfo.getUserDto().getId()));
//        taskFullDto = taskService.save(taskFullDto);
//
//        for (TrelloCardDto trelloCard : trelloCards) {
//            TaskFullDto trelloTask = new TaskFullDto();
//            trelloTask.setName(trelloCard.getName());
//            trelloTask.setWorkerDto(userService.getById(trelloInfo.getUserDto().getId()));
//            trelloTask.setOwnerDto(userService.getById(trelloInfo.getUserDto().getId()));
//            trelloTask.setParentTaskFullDto(taskFullDto);
//            trelloTask.setStatusDto(statusService.getByName(status));
//            taskService.save(trelloTask);
//        }
//    }

    private TrelloBoardDto[] getAllTrelloBoards() {
        TrelloBoardDto[] trelloBoards = null;
        String urlGetAllBoards = String.format("https://trello.com/1/members/%s/boards?key=%s&token=%s",
                trelloInfo.getUserTrelloName(), trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());
        try {
            RestTemplate restTemplate = new RestTemplate();
            String responseAsString = restTemplate.getForObject(urlGetAllBoards, String.class);

            ObjectMapper mapper = new ObjectMapper();
            trelloBoards = mapper.readValue(responseAsString, TrelloBoardDto[].class);

        } catch (Exception e) {
            System.err.println(e);
        }
        return trelloBoards;
    }

    private TrelloListDto[] getTrelloListsByBoard(String idBoard) {
        TrelloListDto[] TrelloLists = null;
        String urlGetListOfBoard = String.format("https://trello.com/1/boards/%s/lists?cards=open&card_fields=name&fields=name&key=%s&token=%s",
                idBoard, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());
        try {
            RestTemplate restTemplate = new RestTemplate();
            String responseAsString = restTemplate.getForObject(urlGetListOfBoard, String.class);

            ObjectMapper mapper = new ObjectMapper();
            TrelloLists = mapper.readValue(responseAsString, TrelloListDto[].class);

        } catch (Exception e) {
            System.err.println(e);
        }
        return TrelloLists;
    }

    private TrelloCardDto[] getTrelloCardsByList(String idList) {
        TrelloCardDto[] TrelloCards = null;

        String urlGetCardsByList = String.format("https://trello.com/1/lists/%s/cards?key=%s&token=%s",
                idList, trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());
        try {
            RestTemplate restTemplate = new RestTemplate();
            String responseAsString = restTemplate.getForObject(urlGetCardsByList, String.class);

            ObjectMapper mapper = new ObjectMapper();
            TrelloCards = mapper.readValue(responseAsString, TrelloCardDto[].class);

        } catch (Exception e) {
            System.err.println(e);
        }
        return TrelloCards;
    }
}

