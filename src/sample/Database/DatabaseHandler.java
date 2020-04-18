package sample.Database;

import sample.controller.LoginController;
import sample.model.Task;
import sample.model.User;

import java.net.UnknownServiceException;
import java.sql.*;


public class DatabaseHandler extends Configs{

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" ;
        
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    // write to Db

//    public void SignUpUser(String firstname, String lastname, String username, String password, String location, String gender){
//        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_FIRSTNAME + ", " + Const.USERS_LASTNAME + "," + Const.USERS_USERNAME
//                            +","+ Const.USERS_PASSWORD + ","+ Const.USERS_LOCATION + ","+ Const.USERS_GENDER + ")" + "VALUE(?,?,?,?,?,?)";

    public void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USERS_FIRSTNAME + ", " + Const.USERS_LASTNAME + "," + Const.USERS_USERNAME
                          +","+ Const.USERS_PASSWORD + ","+ Const.USERS_LOCATION + ","+ Const.USERS_GENDER + ")" + "VALUE(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassWord());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public ResultSet signInUser(User user){
        ResultSet resultSet = null;

        if (!user.getUserName().equals("") && !user.getPassWord().equals("")){
            String query = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_USERNAME + "=?" + " AND " + Const.USERS_PASSWORD + "=?";

            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassWord());
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Username and Password must not be empty");
        }

        return resultSet;
    }


    public void insertTask (Task task){
        String insert = "INSERT INTO " + Const.TASK_TABLE + "(" + Const.USERS_ID + "," + Const.TASKS_DATECREATED + ", " + Const.TASKS_DESCRIPTION + ","
                                         + Const.TASKS_TASK + ")" + "VALUE(?,?,?,?)";
        try {

            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setInt(1, task.getUserID());
            preparedStatement.setTimestamp(2, task.getDateCreated());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setString(4, task.getTask());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public int getAllTask(int userID){
        ResultSet allTask = null;
        int count = 0;

        String query = "SELECT COUNT(*) FROM " + Const.TASK_TABLE + " WHERE " + Const.USERS_ID + " =?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, userID);
            allTask = preparedStatement.executeQuery();

            while (allTask.next()){
              count =  allTask.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return count;
    }


    public ResultSet getTaskbyUser(int userID){
        ResultSet usertasks = null;

        String query = "SELECT * FROM " + Const.TASK_TABLE + " WHERE " + Const.USERS_ID + " = ?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1, userID);
            usertasks = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usertasks;
    }



}
