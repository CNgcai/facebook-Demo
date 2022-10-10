package com.example.demo1;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;



import java.io.IOException;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class dbUtils {
    public static void changeScene(ActionEvent event, String fmxlFile, String title, String username) //String activity
    {
        Parent root = null;

        if( username != null ) {
            try {
                FXMLLoader loader = new FXMLLoader(dbUtils.class.getResource(fmxlFile));
                root = loader.load();
                logInController logInController = loader.getController();
                logInController.setUserInfo(username); //activity);

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                root= FXMLLoader.load(dbUtils.class.getResource(fmxlFile));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
      stage.setScene(new Scene(root, 600,400));
      stage.show();
    }
    public static void SignUp(ActionEvent event, String username, String password) { //create account(
    Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psUserExists = null;
        ResultSet results = null;

        //get.Connection takes 3 parameters
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306:javafxdb", "root", ""); // NB CHECK PASSWORD, attempts to establish connection with database
            psUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ? ");
            psUserExists.setString(1, username); // parameter means how much input are checking for
            results = psUserExists.executeQuery();

            if (results.isBeforeFirst()) //used to check if results set is empty
            {
                System.out.println("User Already Exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username"); //For security purposes don't be too descriptive
                alert.show();
            }
            else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password VALUES (?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
               // psInsert.setString(3, String.valueOf(myDate));
                psInsert.executeUpdate(); //doesn't return anything just updates database

                changeScene(event, "login-in.fxml", "Welcome",username ); //takes us to the login page
            }

        }
        catch (SQLException e) {
            e.printStackTrace();

        }
        finally { // this closes our database connection
            if (results != null) {
                try {
                    results.close();
                }
                catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
            if (psUserExists != null) { //closing connection
                try {
                    psUserExists.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                 try {
                     connection.close();
                 }
                 catch (SQLException e) {
                     e.printStackTrace();
                 }
            }

        }

    }

    public static void loginUser (ActionEvent event, String username, String password) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306:javafxdb", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username =?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User is not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect");
                alert.show();
            }
            else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                  //  Date retrievedTime = resultSet.getDate("time");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "login-in.fxml", "Welcome", username);
                    }
                    else {
                        System.out.println("Password does not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect, Please try again");
                        alert.show();
                    }


                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if ( preparedStatement != null) {
                try {
                    preparedStatement.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
            try {
                connection.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            }
        }

    }
}
