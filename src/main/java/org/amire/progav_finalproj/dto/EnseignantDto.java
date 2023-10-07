package org.amire.progav_finalproj.dto;

import java.sql.Timestamp;

public class EnseignantDto {

    public long idEnseignant;

    public String nom;

    public String prenom;

    public String experience;

    public String evaluations;

    public String competences;

    public String interetsDomaines;

    public String interetsEcoles;

    public String niveauxSouhaites;

    public String adresseElectronique;

    public long telephone;

    public String siteWeb;

    public Timestamp disponibilites;

    public String typeDeContrat;

    public String titresAcademiques;

    public String autresInformations;

    public String references;

    public String login;

    public String password;

    @Override
    public String toString(){
        return "Login:" + login + " ,Password:" + password + " ,Nom:" + nom + ",Prenom:" + prenom;
    }

}
