package org.amire.progav_finalproj.dto;

import org.amire.progav_finalproj.model.PostuleEntity;
import java.util.Date;

public class PostuleListElementDto {
    private final long idPostule;
    private Date datePostule;
    private String initiateur;
    private String decision;
    private String nomEcole;
    private String nomEnseignant;
    private long idEcole;
    private long idEnseignant;

    public PostuleListElementDto(PostuleEntity postulation) {
        this.idPostule = postulation.getIdPostule();
        this.datePostule = Date.from(postulation.getDateCreation().toInstant());
        this.initiateur = postulation.getInitiateur();
        this.decision = postulation.getDecision();
        this.nomEcole = postulation.getEcole().getRaisonSociale();
        this.nomEnseignant = postulation.getEnseignant().getNom() + " " + postulation.getEnseignant().getPrenom();
        this.idEcole = postulation.getEcole().getIdEcole();
        this.idEnseignant = postulation.getEnseignant().getIdEnseignant();
    }

    public PostuleListElementDto(long id){
        this.idPostule = id;
    }

    public long getIdPostule() {
        return idPostule;
    }

    public Date getDatePostule() {
        return datePostule;
    }

    public String getInitiateur() {
        return initiateur;
    }

    public String getDecision() {
        return decision;
    }

    public String getNomEcole() {
        return nomEcole;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public long getIdEcole() {
        return idEcole;
    }

    public long getIdEnseignant() {
        return idEnseignant;
    }
}
