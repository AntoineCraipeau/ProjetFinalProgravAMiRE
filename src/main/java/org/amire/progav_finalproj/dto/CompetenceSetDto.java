package org.amire.progav_finalproj.dto;

import java.util.Arrays;

public class CompetenceSetDto {
    private boolean francais;
    private boolean anglais;
    private boolean philosophie;
    private boolean histoire_geographie;
    private boolean mathematiques;
    private boolean robotique;
    private boolean programmation;
    private boolean svt;
    private boolean physique_chimie;
    private boolean sciences_sociales;
    private boolean psychologie;

    public CompetenceSetDto(String competences) {
        // competences is a string of the form "[francais,anglais,philosophie,mathematiques,robotique,informatique]"
        if(competences == null) {
            this.francais = false;
            this.anglais = false;
            this.philosophie = false;
            this.histoire_geographie = false;
            this.mathematiques = false;
            this.robotique = false;
            this.programmation = false;
            this.svt = false;
            this.physique_chimie = false;
            this.sciences_sociales = false;
            this.psychologie = false;
            return;
        }
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
        this.programmation = Arrays.asList(competenceArray).contains("programmation");
        this.svt = Arrays.asList(competenceArray).contains("svt");
        this.physique_chimie = Arrays.asList(competenceArray).contains("physique_chimie");
        this.sciences_sociales = Arrays.asList(competenceArray).contains("sciences_sociales");
        this.psychologie = Arrays.asList(competenceArray).contains("psychologie");
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
    public boolean getProgrammation() {
        return this.programmation;
    }
    public boolean getSvt() {
        return this.svt;
    }
    public boolean getPhysique_chimie() {
        return this.physique_chimie;
    }
    public boolean getSciences_sociales() {
        return this.sciences_sociales;
    }
    public boolean getPsychologie() {
        return this.psychologie;
    }
}
