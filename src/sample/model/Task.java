package sample.model;

import java.sql.Timestamp;
import java.util.Date;

public class Task {

    private Timestamp dateCreated;
    private String description;
    private String task;
    private int userID;


    public Task() {
    }

    public Task(Timestamp dateCreated, String description, String task, int userID) {
        this.dateCreated = dateCreated;
        this.description = description;
        this.task = task;
        this.userID = userID;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
