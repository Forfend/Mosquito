package com.softserve.mosquito.dtos;

import java.io.Serializable;

/**
 * Generic DTO for Status Entity
 */
public class StatusDto implements Serializable {
    private Long id;
    private String title;

    public StatusDto() {
    }

    public StatusDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
