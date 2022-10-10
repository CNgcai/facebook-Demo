package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class signUpController {

    @FXML
    private TextField confirmPText;

    @FXML
    private Button createAccBttn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField emailTxt;

    @FXML
    private TextField fullNameTxt;

    @FXML
    private Button loginButtin;

    @FXML
    private Label passwordLbl1;

    @FXML
    private TextField passwordTxt;

    @FXML
    private CheckBox privacyPolicy;

    @FXML
    void createButton(ActionEvent event) {
        //not storing other values only care about email and password
        if(!fullNameTxt.getText().trim().isEmpty() && !emailTxt.getText().trim().isEmpty() && !passwordTxt.getText().trim().isEmpty() && !confirmPText.getText().trim().isEmpty() && !privacyPolicy.isSelected() && datePicker.getValue() != null)
        {
            dbUtils.SignUp(event,emailTxt.getText(), passwordTxt.getText() );
        }
        else {
            System.out.println("Please fill in all information");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please ensure all data is provided");
            alert.show();
        }

    }

    @FXML
    void loginBttn(ActionEvent event) {
        dbUtils.changeScene(event, "hello-view.fxml", "Login",null);

    }

}
