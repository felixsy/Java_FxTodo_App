package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.model.Task;

public class CellController extends ListCell<Task> {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label cellTask;

    @FXML
    private Label cellDescription;

    @FXML
    private Label cellDateCreated;

    @FXML
    private ImageView cellDeleteButton;

    private FXMLLoader fxmlLoader ;

    @FXML
    private AnchorPane cellAnchorPane;

    @FXML
    void initialize() {


    }

    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);

        if(empty || task == null){
            setText(null);
            setGraphic(null);
        }else{
            if(fxmlLoader == null){
                fxmlLoader = new FXMLLoader(getClass().getResource("/sample/view/cell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                cellTask.setText(task.getTask());
                cellDescription.setText(task.getDescription());
                cellDateCreated.setText(task.getDateCreated().toString());

                int taskID = task.getTaskID();

                setText(null);
                setGraphic(cellAnchorPane);

                cellDeleteButton.setOnMouseClicked(event ->{
                    getListView().getItems().remove(getItem());
                });


            }
        }
    }
}
