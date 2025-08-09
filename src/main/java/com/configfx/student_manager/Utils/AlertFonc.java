package com.configfx.student_manager.Utils;

import javafx.scene.control.*;
import java.util.Optional;

public class AlertFonc {

    // ✅ Information simple
    public static void showInfo(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText("Information");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // ❌ Erreur
    public static void showError(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // ⚠️ Avertissement
    public static void showWarning(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titre);
        alert.setHeaderText("Avertissement");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // ✅ Confirmation : retourne true si OK
    public static boolean showConfirmation(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType cancelButton = new ButtonType("Non", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(okButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == okButton;
    }

    // 📝 Input Text Dialog
    public static String showInputDialog(String titre, String question, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(titre);
        dialog.setHeaderText(null);
        dialog.setContentText(question);

        Optional<String> result = dialog.showAndWait();
        return result.filter(s -> !s.trim().isEmpty()).orElse(null);
    }
}

