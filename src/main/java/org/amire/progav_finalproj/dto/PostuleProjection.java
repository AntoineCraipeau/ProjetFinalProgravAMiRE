package org.amire.progav_finalproj.dto;
import java.sql.Timestamp;
public class PostuleProjection {
    public long idPostule;
    public Timestamp date;
    public String decision;
    public long idEcole;
    public long idEnseignant;

    public PostuleProjection(long idPostule, Timestamp date, String decision, long idEcole, long idEnseignant) {
        this.idPostule = idPostule;
        this.date = date;
        this.decision = decision;
        this.idEcole = idEcole;
        this.idEnseignant = idEnseignant;
    }

    public String getDecision() {
        return decision;
    }
    public void setDecision(String decision) {
        this.decision = decision;
    }
}