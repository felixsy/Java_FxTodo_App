package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Database.DatabaseHandler;
import sample.model.Task;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AdditemformController {

    private int userID;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField additemTask;

    @FXML
    private TextField additemDescription;

    @FXML
    private Button addtaskBtn;

    @FXML
    private Label taskSuccessLabel;

    @FXML
    private Button myTodosBtn;

    @FXML
    void initialize() {

        addtaskBtn.setOnAction(event -> {
            Task task = new Task();

            Calendar calendar = Calendar.getInstance();
            Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

//            task.setUserID(AdditemController.USERID);
            task.setUserID(LoginController.LOGINUSER);
            task.setDateCreated(timestamp);
            task.setDescription(additemDescription.getText());
            task.setTask(additemTask.getText());

            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.insertTask(task);

            taskSuccessLabel.setVisible(true);

            int taskNumber = 5;
            myTodosBtn.setVisible(true);
            myTodosBtn.setText("My 2DO's " + taskNumber);

            myTodosBtn.setOnAction(event1 -> {
                myTodosBtn.setText("List View " + taskNumber);

            });
        });
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


}
