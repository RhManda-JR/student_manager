package com.configfx.student_manager.Models;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Etudiant {

    public enum Genre{
        Homme,
        Femme
    }
    public enum Statut{
        Payer,
        NonPayer,
        PayerAMoitier
    }

    private String NumCE;
    private String Nom;
    private Statut StatutDroit;
    private String Contact;
    private String Email;
    private Genre genre;

    public boolean isSelection() {
        return selection.get();
    }

    private BooleanProperty selection;

    public Etudiant(String numCE, String nom , Statut statutdroit, String contact , String email, Genre genreT){
        this.NumCE = numCE;
        this.Nom = nom;
        this.StatutDroit = statutdroit;
        this.Contact =  contact;
        this.Email = email;
        this.genre = genreT;
        this.selection = new SimpleBooleanProperty(false);
    }

    public String getNumCE() {
        return NumCE;
    }

    public void setNumCE(String numCE) {
        NumCE = numCE;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public Statut getStatutDroit() {
        return StatutDroit;
    }

    public void setStatutDroit(Statut statutDroit) {
        StatutDroit = statutDroit;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public BooleanProperty selectionProperty() {
        return selection;
    }

    public void setSelection(boolean selection) {
        this.selection.set(selection);
    }


}
