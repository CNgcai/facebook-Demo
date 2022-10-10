package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import java.util.Date;

import java.time.format.DateTimeFormatter;

public class logInController {

    @FXML
    private Label activityLbl;

    @FXML
    private Label loginLbl;

    @FXML
    private Button logoutButton;

    @FXML
    private Label timeLbl;

    @FXML
    private Label usernameLbl;

    @FXML
    void logoutBttn(ActionEvent event) {
        dbUtils.changeScene(event , "hello-view.fxml", "Login", null); //takes us back to log out page

    }


    public void setUserInfo (String username) //String activity)
    {
        //date = new Date();
        usernameLbl.setText("Username: " + username);
       // timeLbl.setText("Time: "+ date.toString()); //maybe could initialise date as string
        activityLbl.setText("Activity:" + "LoggedIn");
       // System.out.println(date);

    }
    //need two databases one for user and other for the events
}

