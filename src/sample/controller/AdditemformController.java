package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.animations.Fader;
import sample.animations.Shaker;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AdditemformController {

    private int userID;

    @FXML
    private AnchorPane addItemAnchor;

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
    private Label failureLabel;

    @FXML
    private Button myTodosBtn;

    @FXML
    void initialize() {

        addtaskBtn.setOnAction(event -> {
            Task task = new Task();

            Calendar calendar = Calendar.getInstance();
            Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

            String enteredTask = additemTask.getText().trim();
            String taskDesc = additemDescription.getText().trim();

            if(enteredTask.equals("") && taskDesc.equals("")){
                Shaker shaker = new Shaker(addItemAnchor);
                shaker.shake();
                failureLabel.setVisible(true);

            }else {

                task.setUserID(LoginController.LOGINUSER);
                task.setDateCreated(timestamp);
                task.setDescription(taskDesc);
                task.setTask(enteredTask);

                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.insertTask(task);

                taskSuccessLabel.setVisible(true);
                failureLabel.setVisible(false);

                int taskNumber = databaseHandler.getAllTask(LoginController.LOGINUSER);
                myTodosBtn.setVisible(true);
                myTodosBtn.setText("My 2DO's " + taskNumber);

                additemTask.clear();
                additemDescription.clear();

                myTodosBtn.setOnAction(event1 -> {

//                myTodosBtn.getScene().getWindow().hide();

//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("/sample/view/list.fxml"));
//
//                try {
//                    fxmlLoader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                Parent root = fxmlLoader.getRoot();
//                Stage stage = new Stage();
//                stage.setScene(new Scene(root));
//                stage.showAndWait();

                    try {
                        AnchorPane myTodoPane = FXMLLoader.load(getClass().getResource("/sample/view/list.fxml"));
//                    addItemAnchor.getChildren().setAll(myTodoPane);
                        Fader fader = new Fader(myTodoPane);
                        fader.Fade();
                        addItemAnchor.getChildren().setAll(myTodoPane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                });

            }
        });
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


}
