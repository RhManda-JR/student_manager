package com.configfx.student_manager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public Button btnconnexion;

    @FXML
    private Button createAccount;

    @FXML
    private AnchorPane formLoginRegisterPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainController mainController = new MainController();
        createAccount.setOnMouseClicked(ev->{
            mainController.loadPageinANCHORPANE("Register",formLoginRegisterPane);
        });

    }
}