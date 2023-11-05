package org.amire.progav_finalproj;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Controleurs {
    public static final String MESSAGE_ERREUR_CREDENTIALS_KO = "Les identifiants sont incorrects";
    public static final String MESSAGE_ERREUR_CREDENTIALS_USED = "Cet identifiant est déjà utilisé";
    public static final String MESSAGE_ERREUR_PROFIL_MODIFICATION_ECHEC = "La modification du profil a échoué";
    public static final String MESSAGE_ERREUR_PROFIL_MODIFICATION_MDP_WRONG_OLD = "La modification du mot de passe a échoué : l'ancien mot de passe est incorrect";
    public static final String MESSAGE_ERREUR_PROFIL_MODIFICATION_MDP_DIFFERENT = "La modification du mot de passe a échoué : les deux mots de passe sont différents";

    public static final String MESSAGE_SUCCES_CREATION_COMPTE = "Votre compte a bien été créé";
    public static final String MESSAGE_SUCCES_MODIFICATION_COMPTE = "Votre compte a bien été modifié";


    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    public void placerUtilisateurDansContexte(HttpServletRequest request);

    public void aiguillerVersLaProchainePage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
