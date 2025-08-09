package com.configfx.student_manager.Controllers;

import animatefx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public AnchorPane MainPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/configfx/student_manager/Views/Login.fxml")));
            Parent root = loader.load();
            LoginController loginController = loader.getController();
            //            condition pour le login
            loginController.btnconnexion.setOnMouseClicked(ev->{
                // condition de login a implementer
                // recuperer les donnees inscrit par l'utilisateur grace au controleur
                //si les donnees sont correcte voici le code a executer

                Stage stage = (Stage)loginController.btnconnexion.getScene().getWindow();
                loadpage("Base",true,stage);
            });
            MainPane.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadpage(String pagename ,Boolean bool, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/configfx/student_manager/Views/" + pagename + ".fxml")));
            Parent root = loader.load();

            // Lier les bords à contentPane
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);

            stage.resizableProperty().setValue(bool);

            MainPane.getChildren().setAll(root);
            new FadeIn(root).play();
        } catch (IOException | NullPointerException e) {
            System.err.println("FXML non trouvé : " + pagename);
        }
    }
    public void loadPageinANCHORPANE(String pagename , AnchorPane anchorPane) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/configfx/student_manager/Views/" + pagename + ".fxml")));
            Parent root = loader.load();

            // Lier les bords à contentPane
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);

            anchorPane.getChildren().setAll(root);
            new FadeIn(root).play();
            new SlideInDown(root).play();
        } catch (IOException | NullPointerException e) {
            System.err.println("FXML non trouvé : " + pagename);
        }
    }

    public void loadWithNewStage(String fxml){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/configfx/student_manager/Views/" + fxml + ".fxml")));
            Parent root = loader.load();
            Scene scene = new Scene(root,800,600);
            Stage stage = new Stage();
            stage.setTitle("Student_manager");
            stage.setScene(scene);

            stage.show();
            new FadeIn(root).play();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
