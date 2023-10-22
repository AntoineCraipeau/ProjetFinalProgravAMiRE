package org.amire.progav_finalproj.dto;

import java.util.Arrays;

public class CompetenceSetDto {
    private boolean francais;
    private boolean anglais;
    private boolean philosophie;
    private boolean histoire_geographie;
    private boolean mathematiques;
    private boolean robotique;
    private boolean informatique;

    public CompetenceSetDto(String competences) {
        competences = competences.replace("[", "");
        competences = competences.replace("]", "");
        competences = competences.replace(" ", "");
        String[] competenceArray = competences.split(",");
        this.francais = Arrays.asList(competenceArray).contains("francais");
        this.anglais = Arrays.asList(competenceArray).contains("anglais");
        this.philosophie = Arrays.asList(competenceArray).contains("philosophie");
        this.histoire_geographie = Arrays.asList(competenceArray).contains("histoire_geographie");
        this.mathematiques = Arrays.asList(competenceArray).contains("mathematiques");
        this.robotique = Arrays.asList(competenceArray).contains("robotique");
        this.informatique = Arrays.asList(competenceArray).contains("informatique");
    }

    public boolean getFrancais() {
        return this.francais;
    }
    public boolean getAnglais() {
        return this.anglais;
    }
    public boolean getPhilosophie() {
        return this.philosophie;
    }
    public boolean getHistoire_geographie() {
        return this.histoire_geographie;
    }
    public boolean getMathematiques() {
        return this.mathematiques;
    }
    public boolean getRobotique() {
        return this.robotique;
    }
    public boolean getInformatique() {
        return this.informatique;
    }
}
