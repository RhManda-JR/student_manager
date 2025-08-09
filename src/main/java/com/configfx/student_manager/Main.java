package com.configfx.student_manager;

import animatefx.animation.FadeIn;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    double x,y;
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Views/Composants/splash.fxml")));
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("S-Manager fac Science");

        stage.resizableProperty().setValue(false);

        //icone
        Image icone = new Image(Objects.requireNonNull(getClass().getResource("src/images/logo.png")).openStream());
        stage.getIcons().add(icone);

        //dragg and drop de la fenetre de base
        root.setOnMousePressed(ev -> {
            x = ev.getSceneX();
            y = ev.getSceneY();
        });
        root.setOnMouseDragged(ev ->{
            stage.setX(ev.getScreenX() - x);
            stage.setY(ev.getScreenY() - y);
        });

        // PLAY THE STAGE
        stage.setScene(scene);
        stage.show();

        // splashscreen
        PauseTransition delay = getPauseTransition(stage);
        delay.play();

        // animation
        new FadeIn(root).play();
    }

    private PauseTransition getPauseTransition(Stage stage) {
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> {
            try {
                FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("Views/Main.fxml"));
                Parent mainRoot = mainLoader.load();
                stage.setMinWidth(820);
                stage.setMinHeight(600);
                stage.setScene(new Scene(mainRoot,800,600));
                stage.show();
                new FadeIn(mainRoot).play();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return delay;
    }


    public static void main(String[] args) {
        launch();
    }
}