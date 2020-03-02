package ru.lavrov.tm.entity;

import java.util.Date;
import java.util.UUID;

public class Task {
    private String name;
    private String id = UUID.randomUUID().toString();;
    private int description;
    private Date startDate;
    private Date finishDate = new Date();
    private String projectId = null;
    private String userId;

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, String projectId, String userId) {
        this.name = name;
        this.projectId = projectId;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", description=" + description +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", projectId='" + projectId + '\'' +
                ", userId='" + userId + '\'';
    }
}
