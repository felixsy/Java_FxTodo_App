package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;

import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/addItem.fxml"));

        primaryStage.setTitle("TODO APP");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        launch(args);
        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.getDbConnection();



    }
}
