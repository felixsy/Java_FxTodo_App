package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.animations.Fader;
import sample.animations.Shaker;
import sample.model.User;

public class SignupController {

    @FXML
    private AnchorPane signupPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signupFirstname;

    @FXML
    private TextField signupLastname;

    @FXML
    private TextField signupUsername;

    @FXML
    private TextField signupPassword;

    @FXML
    private CheckBox signupCheckBoxMale;

    @FXML
    private CheckBox signupCheckBoxFemale;

    @FXML
    private TextField signupLocation;

    @FXML
    private Button signupButton;

    @FXML
    private Label signupSuccessLabel;

    @FXML
    private Button signupLoginBtn;

    @FXML
    void initialize() {

        signupButton.setOnAction(event -> {
            createUser();
            Shaker shaker = new Shaker(signupButton);
            shaker.shake();

        });

        signupLoginBtn.setOnAction(event -> {
            loadLoginScreen();
        });

        clearCheckBox();
    }

    public void clearCheckBox(){
        signupCheckBoxFemale.setOnAction(event -> {
            signupCheckBoxMale.setSelected(false);
        });

        signupCheckBoxMale.setOnAction(event -> {
            signupCheckBoxFemale.setSelected(false);
        });
    }



    private void createUser() {

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String firstName = signupFirstname.getText();
        String lastName = signupLastname.getText();
        String userName = signupUsername.getText();
        String passWord = signupPassword.getText();
        String location = signupLocation.getText();
        String gender ="";

        if(signupCheckBoxMale.isSelected()){
            gender = "Male";
        }else{
            gender = "Female";
        }

        if(!firstName.equals("") && !lastName.equals("") && !signupUsername.equals("") && !signupLocation.equals("")){
            User user = new User(firstName, lastName, userName, passWord, location, gender);

            databaseHandler.signUpUser(user);

            signupFirstname.clear();
            signupLastname.clear();
            signupLocation.clear();
            signupUsername.clear();
            signupPassword.clear();

            signupSuccessLabel.setVisible(true);
            signupLoginBtn.setVisible(true);

        }else{
            signupSuccessLabel.setVisible(true);
            signupSuccessLabel.setText("Signup Incomplete! Fill All Field");
            signupSuccessLabel.setTextFill(Paint.valueOf("red"));
            signupLoginBtn.setVisible(true);
        }
    }



    public void loadLoginScreen(){

//        signupButton.getScene().getWindow().hide();
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("/sample/view/login.fxml"));

        try {
            AnchorPane loginPane = FXMLLoader.load(getClass().getResource("/sample/view/login.fxml"));

            Fader fader = new Fader(loginPane);
            fader.Fade();

            signupPane.getChildren().setAll(loginPane);

        } catch (IOException e) {
            e.printStackTrace();
        }


//        try {
//            fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Parent root = fxmlLoader.getRoot();
//        Stage stage = new Stage();
//        stage.setTitle("TODO APP || Login Screen");
//        stage.setScene(new Scene(root));
//        stage.showAndWait();
    }
}
