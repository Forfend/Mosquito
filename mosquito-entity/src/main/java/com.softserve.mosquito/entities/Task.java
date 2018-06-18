package com.softserve.mosquito.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Task parentTask;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_id")
    private User worker;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "estimation_id")
    private Estimation estimation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "trello_id")
    private String trelloId;


    @OneToMany(mappedBy = "task", targetEntity = Comment.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "parentTask", targetEntity = Task.class, fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Task> childTasks = new ArrayList<>();

    public Task() {
    }

    public Task(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Estimation getEstimation() {
        return estimation;
    }

    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public List<Task> getChildTasks() {
        return childTasks;
    }

    public void setChildTasks(List<Task> childTasks) {
        this.childTasks = childTasks;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    public String getTrelloId() {
        return trelloId;
    }

    public void setTrelloId(String trelloId) {
        this.trelloId = trelloId;
    }

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    public static class TaskBuilder {
        private Task task;

        private TaskBuilder() {
            task = new Task();
        }

        public TaskBuilder id(Long id) {
            task.id = id;
            return this;
        }

        public TaskBuilder name(String name) {
            task.name = name;
            return this;
        }

        public TaskBuilder parentTask(Task parentTask) {
            task.parentTask = parentTask;
            return this;
        }

        public TaskBuilder owner(User owner) {
            task.owner = owner;
            return this;
        }

        public TaskBuilder worker(User worker) {
            task.worker = worker;
            return this;
        }

        public TaskBuilder estimation(Estimation estimation) {
            task.estimation = estimation;
            return this;
        }

        public TaskBuilder priority(Priority priority) {
            task.priority = priority;
            return this;
        }

        public TaskBuilder status(Status status) {
            task.status = status;
            return this;
        }

        public TaskBuilder comments(List<Comment> comments) {
            task.comments = comments;
            return this;
        }

        public TaskBuilder childTasks(List<Task> childTasks) {
            task.childTasks = childTasks;
            return this;
        }

        public Task build() {
            return task;
        }

    }
}
