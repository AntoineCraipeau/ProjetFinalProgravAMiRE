package org.amire.progav_finalproj;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Controleurs {
    public static final String MESSAGE_ERREUR_CREDENTIALS_KO = "Les identifiants sont incorrects";
    public static final String MESSAGE_ERREUR_CREDENTIALS_VIDE = "Veuillez remplir les champs";
    public static final String MESSAGE_ERREUR_CREDENTIALS_USED = "Cet identifiant est déjà utilisé";

    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    public void placerUtilisateurDansContexte(HttpServletRequest request);

    public void aiguillerVersLaProchainePage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
