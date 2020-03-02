package ru.lavrov.tm.entity;

import java.util.Date;
import java.util.UUID;

public class Project {
    private String name;
    private String id = UUID.randomUUID().toString();;
    private int description;
    private Date startDate = new Date();
    private Date finishDate = null;
    private String userId;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, String userId) {
        this.name = name;
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
                ", userId='" + userId + '\'';
    }
}
