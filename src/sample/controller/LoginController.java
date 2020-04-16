package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Database.DatabaseHandler;
import sample.animations.Fader;
import sample.animations.Shaker;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {

    public static int LOGINUSER;

    private int userId;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loginOuterPane;

    @FXML
    private AnchorPane loginInnerPane;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginLoginBtn;

    @FXML
    private Button loginSignupBtn;

    @FXML
    private Text loginTitle;

    private DatabaseHandler databaseHandler;

    private Shaker shaker ;


    @FXML
    void initialize() {

        loginSignupBtn.setOnAction(event -> {

            try {
                AnchorPane signupPane = FXMLLoader.load(getClass().getResource("/sample/view/signup.fxml"));

                Fader fader = new Fader(signupPane);
                fader.Fade();

                loginOuterPane.getChildren().setAll(signupPane);

            } catch (IOException e) {
                e.printStackTrace();
            }



//            loginSignupBtn.getScene().getWindow().hide();
//
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("/sample/view/signup.fxml"));
//
//            try {
//                fxmlLoader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            Parent root = fxmlLoader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.showAndWait();
        });


      loginLoginBtn.setOnAction(event->{
          String loginTxt = loginUsername.getText().trim();
          String loginPwd = loginPassword.getText().trim();

          if(!loginTxt.equals("") && !loginPwd.equals("")){
              try {
                  loginUser(loginTxt, loginPwd);
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }else{
              System.out.println("Login failed");
              loginUsername.setPromptText("Enter A valid username");
              shaker = new Shaker(loginUsername);
              shaker.shake();

              loginPassword.setPromptText("Enter A valid password");
              Shaker shakerPwd = new Shaker(loginPassword);
              shakerPwd.shake();

          }
      });

    }

    private void loginUser( String username, String password) throws SQLException {
        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);

        databaseHandler = new DatabaseHandler();
        ResultSet userRow = databaseHandler.signInUser(user);

        int counter = 0;

        while (userRow.next()) {
            counter++;

            String loginUser = userRow.getString("firstname");
            int loginUserID = userRow.getInt("userid");

            setUserId(loginUserID);
            LOGINUSER = loginUserID;
            AdditemController.USERID = loginUserID;


            System.out.println("Welcome " + loginUser);
        }

        if(counter == 1){
            loadAddItemscreen();
        }else{
            System.out.println("Invalid Login Credentials");
            
            loginUsername.setPromptText("Enter a valid username");
            shaker = new Shaker(loginUsername);
            shaker.shake();

            loginPassword.setPromptText("Enter a valid password");
            Shaker shakerPwd = new Shaker(loginPassword);
            shakerPwd.shake();
        }
    }

    public void loadAddItemscreen(){
        System.out.println("Login Successful");
        loginSignupBtn.getScene().getWindow().hide();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/sample/view/addItem.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("TODO APP || Add Task Screen");
        stage.setScene(new Scene(root));

        AdditemController additemController = fxmlLoader.getController();
        additemController.setUserID(getUserId());
        stage.showAndWait();
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
