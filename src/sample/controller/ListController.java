package sample.controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.Database.DatabaseHandler;
import sample.model.Task;

public class ListController {

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
    private ListView<Task> myListView;


    @FXML
    void initialize() throws SQLException {

        ObservableList<Task> tasklist = FXCollections.observableArrayList();

        Calendar calendar = Calendar.getInstance();
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());



        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet taskbyUser = databaseHandler.getTaskbyUser(LoginController.LOGINUSER);
        while(taskbyUser.next()){
            Task task = new Task();
            task.setTask(taskbyUser.getString("task"));
            task.setDescription(taskbyUser.getString("description"));
            task.setDateCreated(taskbyUser.getTimestamp("datecreated"));
            tasklist.addAll(task);
        }

        myListView.setItems(tasklist);
        myListView.setCellFactory(CellController -> new CellController());

    }
}
