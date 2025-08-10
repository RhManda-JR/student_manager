package com.configfx.student_manager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
        //j'ai deplacer ici le bouton de connexion car c'est ici le loginController
        //DONC ICI LE DEBUT DE LA CONDITION DE LOGIN
            //On peut faire une fonction specifique que l'on peut reutiliser en cas d'authentification
            btnconnexion.setOnMouseClicked(ev->{
                AnchorPane ap = mainController.getOldestAnchorPane(btnconnexion);
                Stage stage = (Stage)ap.getScene().getWindow();
                mainController.loadpage("Base",true,stage,ap);
            });
        //FIN DE CONDITION LOGIN

    }
}