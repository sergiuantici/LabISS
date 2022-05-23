package com.bug.bugiss2.domain;

import java.io.Serializable;

public class Bug implements Serializable {
    private String name;
    private String description;
    private String status;
    private Long id;
    private static final long serialVersionUID = 7331115341259248461L;
    public Bug(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bug() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
