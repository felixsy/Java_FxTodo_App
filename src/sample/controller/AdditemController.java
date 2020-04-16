package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import sample.animations.Fader;
import sample.animations.Shaker;

public class AdditemController {

    public static int USERID;

    private int userID;

    @FXML
    private AnchorPane addItemPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label additemLabel;

    @FXML
    private ImageView addButton;

    @FXML
    void initialize() {
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker shaker =new Shaker(addButton);
            shaker.shake();
            clearAddItemScreen();
            addItemPane();
        });

    }

    public void clearAddItemScreen(){

        additemLabel.relocate(5f, 25f);
        additemLabel.setOpacity(0f);

        addButton.relocate(0f, 10f);
        addButton.setOpacity(0f);
    }

    public void addItemPane(){
        try {
            AnchorPane itemPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemform.fxml"));

            Fader fader = new Fader(itemPane);
            fader.Fade();

            AdditemformController additemformController = new AdditemformController();
            additemformController.setUserID(getUserID());

            addItemPane.getChildren().setAll(itemPane);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {

        this.userID = userID;

    }

    
}
