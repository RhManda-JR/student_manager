package com.configfx.student_manager.Controllers;

import animatefx.animation.SlideInUp;
import com.configfx.student_manager.Models.Etudiant;
import com.configfx.student_manager.Utils.AlertFonc;
import com.configfx.student_manager.Utils.PdfGenerator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListeEtudiantController implements Initializable {

    @FXML
    private TableColumn<Etudiant, Void> ActionCol;

    @FXML
    private TableColumn<Etudiant, Boolean> CheckCol;

    @FXML
    private TableColumn<Etudiant, String> ContactCol;

    @FXML
    private TableColumn<Etudiant, String> EmailCol;

    @FXML
    private TableColumn<Etudiant, Etudiant.Genre> GenreCol;

    @FXML
    private TableColumn<Etudiant, String> NomCol;

    @FXML
    private TableColumn<Etudiant, String> NumCeCol;

    @FXML
    private TableColumn<Etudiant, Etudiant.Statut> StatutDroitCol;

    @FXML
    private TableView<Etudiant> tabview;

    @FXML
    private Label CounterLabel;

    @FXML
    private Label LabelSelect;

    @FXML
    private AnchorPane CounterSelectPane;

    @FXML
    private CheckBox selectAllRow;


    @FXML
    private Button btnPrintSelected;

    @FXML
    private Button btnSuppSelected;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // load table
        Platform.runLater(this::LoadDataTable);
        FonctionToExcecute();
    }
    
    private void FonctionToExcecute() {
        SupprimerSelection();
        imprimerSelection();
    }

    public void LoadDataTable(){

        //fonction appeller en premier
        updateSelectionCoun();
        ShowHideSubmenuSlection();

        //code pour la table
        NumCeCol.setCellValueFactory( new PropertyValueFactory<>("NumCE"));
        NomCol.setCellValueFactory( new PropertyValueFactory<>("Nom"));
        StatutDroitCol.setCellValueFactory( new PropertyValueFactory<>("StatutDroit"));
        ContactCol.setCellValueFactory( new PropertyValueFactory<>("Contact"));
        EmailCol.setCellValueFactory( new PropertyValueFactory<>("Email"));
        GenreCol.setCellValueFactory( new PropertyValueFactory<>("genre"));
        ActionCol.setCellValueFactory( new PropertyValueFactory<>(""));

        // Pour la colonne CheckBox
        CheckCol.setCellValueFactory(cellData -> cellData.getValue().selectionProperty());
        CheckCol.setCellFactory(tableColumn -> {
            CheckBoxTableCell<Etudiant, Boolean> cell = new CheckBoxTableCell<>();
            cell.setEditable(true);
            return cell;
        });

        // 3. Gère la selection des lignes et le clic sur le header CheckBox
        SelectedRow();

        //Ajout de bouton dans la cellule Action
        AddButtonInCellAction();


        tabview.setItems(listEtudiant());
    }

    private void SelectedRow() {
        selectAllRow.setOnAction(et -> {
            // si c'est true ou false la valeur serra envoyer a setSelection
            boolean selected = selectAllRow.isSelected();
            for (Etudiant etu : tabview.getItems()) {
                etu.setSelection(selected);
            }
            updateSelectionCoun(); // met à jour le label
            ShowHideSubmenuSlection(); //show submenu
        });
        Platform.runLater(() -> {
            for (Etudiant etu : tabview.getItems()) {
                etu.selectionProperty().addListener((obs, wasSelected, isNowSelected) -> {
                    updateSelectionCoun(); // met à jour le label
                    ShowHideSubmenuSlection(); //show submenu
                    boolean allSelected = tabview.getItems().stream().allMatch(e -> e.selectionProperty().get());
                    boolean noneSelected = tabview.getItems().stream().noneMatch(e -> e.selectionProperty().get());

                    // Mettez à jour le checkbox principal en fonction de l’état
                    if (allSelected || noneSelected) {
                        selectAllRow.setSelected(allSelected);
                        selectAllRow.setIndeterminate(false);
                    } else {
                        // si partiellement sélectionné
                        selectAllRow.setIndeterminate(true);
                    }
                });
            }
        });
    }

    private void AddButtonInCellAction() {
        ActionCol.setCellFactory(col -> new TableCell<>() {
            private final Button editBtn = new Button();
            private final Button viewBtn = new Button();
            private final Button deleteBtn = new Button();
            private final HBox hbox = new HBox(2, editBtn, deleteBtn,viewBtn);

            {
                hbox.setAlignment(Pos.CENTER);

                HBox.setHgrow(editBtn, Priority.ALWAYS);
                HBox.setHgrow(viewBtn, Priority.ALWAYS);
                HBox.setHgrow(deleteBtn, Priority.ALWAYS);

                editBtn.setMaxWidth(Double.MAX_VALUE);
                viewBtn.setMaxWidth(Double.MAX_VALUE);
                deleteBtn.setMaxWidth(Double.MAX_VALUE);



                //  Style (optionnel, si tu as du CSS)
                editBtn.getStyleClass().add("btn-edit");
                viewBtn.getStyleClass().add("btn-preview");
                deleteBtn.getStyleClass().add("btn-supp");

                //  Action Edit
                editBtn.setOnAction(e -> {
                    Etudiant etu = getTableView().getItems().get(getIndex());
                    System.out.println("Édition de : " + etu.getNom());
                    // Ouvre une nouvelle fenêtre, modale ou éditeur...
                    AlertFonc.showInfo("Modification de l'étudiant : " + etu.getNom()
                    ,"Ici tu peux ouvrir une fenêtre pour modifier les infos.");
                });

                //  Action View
                viewBtn.setOnAction(e -> {
                    Etudiant etu = getTableView().getItems().get(getIndex());
                    String info ="Num CE : " + etu.getNumCE()
                                + "\nContact : " + etu.getContact()
                                + "\nEmail : " + etu.getEmail()
                                + "\nGenre : " + etu.getGenre()
                                + "\nStatut : " + etu.getStatutDroit();
                    AlertFonc.showInfo("Nom : " + etu.getNom(),info);
                });

                // Action Delete
                deleteBtn.setOnAction(e -> {
                    Etudiant etu = getTableView().getItems().get(getIndex());

                    if (AlertFonc.showConfirmation("Supprimer l'étudiant : " + etu.getNom() + " ?","Cette action est irréversible.")) {
                        getTableView().getItems().remove(etu);
                        updateSelectionCoun();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : hbox);
            }
        });
    }
    
    private void updateSelectionCoun() {
        long count = tabview.getItems().stream()
                .filter(e -> e.selectionProperty().get())
                .count();
        CounterLabel.setText(String.valueOf(count));
    }

    private void ShowHideSubmenuSlection(){
        Integer count = Integer.valueOf(CounterLabel.getText());
        Boolean Sup0 = count > 0 ;
        CounterSelectPane.setVisible(Sup0);
        if(count > 1){
            LabelSelect.setText("Selections");
        }else{
            LabelSelect.setText("Selection");
        }
        new SlideInUp(CounterSelectPane);
    }

    private void SupprimerSelection(){
        btnSuppSelected.setOnAction(e -> {
            // Crée une copie des éléments sélectionnés
            List<Etudiant> selectionASupprimer = new ArrayList<>();

            for (Etudiant etudiant : tabview.getItems()) {
                if (etudiant.selectionProperty().get()) {
                    selectionASupprimer.add(etudiant);
                }
            }
            // Supprime de la liste observable (mise à jour automatique de la table)
            if (AlertFonc.showConfirmation("Supprimer les éléments sélectionnés ?","Cette action est irréversible.")) {
                tabview.getItems().removeAll(selectionASupprimer);
            }
        });
    }
    
    private void imprimerSelection(){
        btnPrintSelected.setOnAction(e -> {
            List<Etudiant> selectionnes = tabview.getItems().stream()
                    .filter(etu -> etu.selectionProperty().get())
                    .toList();
            try {
                if(PdfGenerator.exporterEtudiants(selectionnes)){
                    AlertFonc.showInfo("Succes","Liste imprimer !!");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });
    }

    public ObservableList<Etudiant> listEtudiant(){
        ObservableList<Etudiant> listetudiant = FXCollections.<Etudiant> observableArrayList();

        Etudiant Et1 = new Etudiant("M001000","Harim",Etudiant.Statut.NonPayer,"034 00 001 02","harim@gmail.com",Etudiant.Genre.Homme);
        Etudiant Et2 = new Etudiant("M001001","GasyPloaty",Etudiant.Statut.Payer,"037 70 001 45","gasyploaty@gmail.com",Etudiant.Genre.Femme);
        Etudiant Et3 = new Etudiant("M001002","Ljo",Etudiant.Statut.PayerAMoitier,"033 55 201 09","ljo@gmail.com",Etudiant.Genre.Homme);


        listetudiant.addAll(Et1,Et2,Et3);

        return listetudiant;
    }

}
