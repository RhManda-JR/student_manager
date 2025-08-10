package com.configfx.student_manager.Controllers;

import animatefx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
        loadPageinANCHORPANE("Login",MainPane);
    }

    public void loadpage(String pagename ,Boolean bool, Stage stage,AnchorPane pane) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/configfx/student_manager/Views/" + pagename + ".fxml")));
            Parent root = loader.load();

            // Lier les bords à contentPane
            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);

            stage.resizableProperty().setValue(bool);
            // Faire apparaitre la fenetre au centre
            stage.centerOnScreen();

            //changement de l'Anchorpane statique en dynamique
            //* Avant : MainPane
            //* Apres la variable pane que j'ai mis en parametre
            pane.getChildren().setAll(root);
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

    // Prendre l'anchorpane le plus haut du hierarchie
    public AnchorPane getOldestAnchorPane(Node node) {
        Node current = node;
        AnchorPane oldest = null;

        while (current != null) {
            if (current instanceof AnchorPane) {
                oldest = (AnchorPane) current; // garde le dernier AnchorPane trouvé
            }
            current = current.getParent(); // on monte d’un niveau
        }

        return oldest; // c’est le plus vieux trouvé
    }


}
