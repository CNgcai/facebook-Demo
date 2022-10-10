package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class HelloController {

    @FXML
    private Button createButton;
    @FXML
    private TextField usernameText;

    @FXML
    private AnchorPane emailTxt;

    @FXML
    private Button loginButton;

    @FXML
    private Label passwordLbl;

    @FXML
    private Label passwordLbl1;

    @FXML
    private TextField passwordTxt;

    @FXML
    void createButton(ActionEvent event) {
        dbUtils.changeScene(event, "sign-up.fxml", "Sign Up", null);

    }

    @FXML
    void loginBttn(ActionEvent event) { //login functionality
        dbUtils.loginUser(event, usernameText.getText(), passwordTxt.getText());

    }

}

