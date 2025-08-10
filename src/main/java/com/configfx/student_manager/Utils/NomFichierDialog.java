package com.configfx.student_manager.Utils;

import javafx.scene.control.*;

import java.nio.file.*;
import java.util.Optional;

//pour demander le nom du fichier pdf a telecharger

public class NomFichierDialog {

    public static String getNomFichierUnique(Path dossier) {
        try {
            // Demande à l'utilisateur
            TextInputDialog dialog = new TextInputDialog("Liste_Etudiants");
            dialog.setTitle("Nom du fichier");
            dialog.setHeaderText(null);
            dialog.setContentText("Entrez le nom du fichier :");

            Optional<String> result = dialog.showAndWait();
            if (result.isEmpty() || result.get().isBlank()) {
                return null;
            }

            String baseNom = result.get().trim();

            Path fichier = dossier.resolve("PdfEtudiantListe/"+ baseNom + ".pdf");
            System.out.print(fichier+"\n");

            // Vérifie si fichier existe
            if (Files.exists(fichier)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Fichier existant");
                alert.setHeaderText("Un fichier avec ce nom existe déjà.");
                alert.setContentText("Que voulez-vous faire ?");

                ButtonType ecraser = new ButtonType("Écraser");
                ButtonType renommer = new ButtonType("Renommer");
                ButtonType annuler = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(ecraser, renommer, annuler);

                Optional<ButtonType> choix = alert.showAndWait();
                if (choix.isEmpty() || choix.get() == annuler) {
                    return null;
                } else if (choix.get() == renommer) {
                    fichier = getNomFichierUnique(dossier, baseNom);
                }
                // sinon on garde le nom de base
            }

            System.out.print(fichier+"\n");
            return fichier.getFileName().toString(); // retourne seulement le nom du fichier (ex: "Liste_Etudiants.pdf")

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Path getNomFichierUnique(Path dossier, String baseNom) {
        int i = 1;
        Path fichier;
        do {
            fichier = dossier.resolve(baseNom + "_" + i + ".pdf");
            i++;
        } while (Files.exists(fichier));
        return fichier;
    }
}


