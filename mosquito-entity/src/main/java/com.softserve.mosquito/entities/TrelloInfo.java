//package com.softserve.mosquito.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "users_trello")
//public class TrelloInfo implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    @JsonIgnore
//    private Long userId;
//
//    @JoinColumn(name = "username")
//    private String userTrelloName;
//
//    @JoinColumn(name = "access_key")
//    private String userTrelloKey;
//
//    @JoinColumn(name = "access_token")
//    private String userTrelloToken;
//
//
//    public TrelloInfo() {
//    }
//
//    public TrelloInfo(Long userId, String userTrelloName, String userTrelloKey, String userTrelloToken) {
//        this.userId = userId;
//        this.userTrelloName = userTrelloName;
//        this.userTrelloKey = userTrelloKey;
//        this.userTrelloToken = userTrelloToken;
//    }
//
//    public TrelloInfo(Long id, Long userId, String userTrelloName, String userTrelloKey, String userTrelloToken) {
//        this.id = id;
//        this.userId = userId;
//        this.userTrelloName = userTrelloName;
//        this.userTrelloKey = userTrelloKey;
//        this.userTrelloToken = userTrelloToken;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getUserTrelloName() {
//        return userTrelloName;
//    }
//
//    public void setUserTrelloName(String userTrelloName) {
//        this.userTrelloName = userTrelloName;
//    }
//
//    public String getUserTrelloKey() {
//        return userTrelloKey;
//    }
//
//    public void setUserTrelloKey(String userTrelloKey) {
//        this.userTrelloKey = userTrelloKey;
//    }
//
//    public String getUserTrelloToken() {
//        return userTrelloToken;
//    }
//
//    public void setUserTrelloToken(String userTrelloToken) {
//        this.userTrelloToken = userTrelloToken;
//    }
//
//    @Override
//    public String toString() {
//        return "TrelloInfo{" +
//                "userId=" + userId +
//                ", userTrelloName='" + userTrelloName + '\'' +
//                ", userTrelloKey='" + userTrelloKey + '\'' +
//                ", userTrelloToken='" + userTrelloToken + '\'' +
//                '}';
//    }
//}
