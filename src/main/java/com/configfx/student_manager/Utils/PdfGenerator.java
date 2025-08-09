package com.configfx.student_manager.Utils;
import com.configfx.student_manager.Models.Etudiant;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class PdfGenerator {

    public static boolean exporterEtudiants(List<Etudiant> etudiants) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(MakeFile("PdfEtudiantListe")));
            document.open();

            // Titre
            Font fontTitre = new Font(Font.HELVETICA, 18, Font.BOLD);
            Paragraph titre = new Paragraph("Liste des étudiants sélectionnés", fontTitre);
            titre.setAlignment(Element.ALIGN_CENTER);
            document.add(titre);

            document.add(new Paragraph(" ")); // espace

            // Tableau
            PdfPTable table = new PdfPTable(5); // colonnes : Nom, NumCE, Contact, Email, Genre
            table.setWidthPercentage(100);
            table.setWidths(new int[]{2, 2, 2, 3, 2});

            // En-têtes
            table.addCell("Nom");
            table.addCell("NumCE");
            table.addCell("Contact");
            table.addCell("Email");
            table.addCell("Genre");

            for (Etudiant e : etudiants) {
                table.addCell(e.getNom());
                table.addCell(e.getNumCE());
                table.addCell(e.getContact());
                table.addCell(e.getEmail());
                table.addCell(e.getGenre().toString());
            }

            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return false;
    }

    public static File MakeFile(String foldername) throws IOException {
        Path desktopPath = Paths.get(System.getProperty("user.home"), "OneDrive", "Desktop");

        if (!Files.exists(desktopPath)) {
            // fallback au Desktop normal
            desktopPath = Paths.get(System.getProperty("user.home"), "Desktop");
        }

        Path folder = (desktopPath).resolve(foldername);
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        }
        Path file = folder.resolve(Objects.requireNonNull(NomFichierDialog.getNomFichierUnique(desktopPath)));

        // Crée le dossier s’il n’existe pas

        System.out.print("Dossier creer dans: " + desktopPath + "\n");
        return new File(file.toUri());
    }
}
