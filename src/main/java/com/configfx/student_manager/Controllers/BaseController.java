package com.configfx.student_manager.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable {

    @FXML
    private AnchorPane basepagepane;

    @FXML
    private Button btndashboard;

    @FXML
    private Button btndeconnexion;

    @FXML
    private Button btnlistetudiant;

    @FXML
    private Button btnparametre;

    @FXML
    private Pane icondash;

    @FXML
    private Pane iconlist;

    @FXML
    private Pane iconlogout;

    @FXML
    private Pane iconparam;

    @FXML
    private ImageView profileimageview;

    MainController mainController = new MainController();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //load dashboard
        mainController.loadPageinANCHORPANE("dashboard",basepagepane);
        //navigation
        Navigation();
    }

    public void Navigation(){
        //navigation
        btndashboard.setOnMouseClicked(e->{
            mainController.loadPageinANCHORPANE("dashboard",basepagepane);
            Selected(btndashboard,icondash,"dash2");
        });

        btnlistetudiant.setOnMouseClicked(e->{
            mainController.loadPageinANCHORPANE("liste_etudiant",basepagepane);
            Selected(btnlistetudiant,iconlist,"listEtud2");
        });

        btnparametre.setOnMouseClicked(e->{
            mainController.loadPageinANCHORPANE("parametre",basepagepane);
            Selected(btnparametre,iconparam,"param2");
        });

        btndeconnexion.setOnMouseClicked(e->{
            mainController.loadPageinANCHORPANE("Login",mainController.MainPane);
            Selected(btndeconnexion,iconlogout,"logout");
        });
    }

    public void Selected(Button btn ,Pane icon,String iconName){
        String style2 = "-fx-background-color:transparent;" +
                        "-fx-text-fill:black;";
        btndashboard.setStyle(style2);
        btnlistetudiant.setStyle(style2);
        btnparametre.setStyle(style2);
        btndeconnexion.setStyle(style2);



        String iconDashstyle =  "-fx-background-image:url("+geturlIcon("dash1")+")";
        String iconListstyle =  "-fx-background-image:url("+geturlIcon("listEtud1")+")";
        String iconParamstyle =  "-fx-background-image:url("+geturlIcon("param1")+")";
        String iconSelected =  "-fx-background-image:url("+geturlIcon(iconName)+")";

        icondash.setStyle(iconDashstyle);
        iconlist.setStyle(iconListstyle);
        iconparam.setStyle(iconParamstyle);


        //icon

        String style =  "-fx-background-color:E4E4E4;" +
                "-fx-text-fill:#1ec090;";

        btn.setStyle(style);
        icon.setStyle(iconSelected);
    }

    public URL geturlIcon(String iconName){
        return getClass().getResource("/com/configfx/student_manager" +
        "/src/images/icon/"+iconName+".png");
    }


}
