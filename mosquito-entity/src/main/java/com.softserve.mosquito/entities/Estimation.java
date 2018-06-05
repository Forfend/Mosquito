package com.softserve.mosquito.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "estimations")
public class Estimation implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "estimation")
    private Integer timeEstimation;
    private Integer remaining;
    @OneToOne(mappedBy = "estimation")
    private Task task;
    @OneToMany(mappedBy = "estimation")
    private List<LogWork> logWorks;

    public Estimation() {}

    public Estimation(int timeEstimation) { this.timeEstimation = timeEstimation;}


    public Estimation(Long id, Integer timeEstimation, Integer remaining) {
        this.timeEstimation = timeEstimation;
        this.id = id;
        this.remaining = remaining;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeEstimation() {
        return timeEstimation;
    }

    public void setTimeEstimation(Integer timeEstimation) {
        this.timeEstimation = timeEstimation;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }

    public List<LogWork> getLogWorks() {
        return logWorks;
    }

    public void setLogWorks(List<LogWork> logWorks) {
        this.logWorks = logWorks;
    }

    @Override
    public String toString() {
        return "Estimation{" +
                "id=" + id +
                ", timeEstimation=" + timeEstimation +
                ", remaining=" + remaining +
                ", logWorks=" + logWorks +
                '}';
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
