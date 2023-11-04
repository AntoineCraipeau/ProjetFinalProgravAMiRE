package org.amire.progav_finalproj.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "postule", schema = "db_prograv_final")
public class PostuleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_postule", nullable = false)
    private long idPostule;
    @Basic
    @Column(name = "Date_Creation", nullable = true)
    private Date dateCreation;
    @Basic
    @Column(name = "Initiateur", nullable = true, length = 255)
    private String initiateur;
    @Basic
    @Column(name = "Decision", nullable = true, length = 255)
    private String decision;
    @Basic
    @Column(name = "id_ecole", nullable = false, insertable = false, updatable = false)
    private long idEcole;
    @Basic
    @Column(name = "id_enseignant", nullable = false, insertable = false, updatable = false)
    private long idEnseignant;
    @ManyToOne
    @JoinColumn(name = "id_ecole", referencedColumnName = "id_ecole", nullable = false)
    private EcoleEntity ecole;
    @ManyToOne
    @JoinColumn(name = "id_enseignant", referencedColumnName = "id_enseignant", nullable = false)
    private EnseignantEntity enseignant;

    public long getIdPostule() {
        return idPostule;
    }

    public void setIdPostule(long idPostule) {
        this.idPostule = idPostule;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getInitiateur() {
        return initiateur;
    }

    public void setInitiateur(String initiateur) {
        this.initiateur = initiateur;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public long getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(long idEcole) {
        this.idEcole = idEcole;
    }

    public long getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(long idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostuleEntity that = (PostuleEntity) o;

        if (idPostule != that.idPostule) return false;
        if (idEcole != that.idEcole) return false;
        if (idEnseignant != that.idEnseignant) return false;
        if (dateCreation != null ? !dateCreation.equals(that.dateCreation) : that.dateCreation != null) return false;
        if (initiateur != null ? !initiateur.equals(that.initiateur) : that.initiateur != null) return false;
        if (decision != null ? !decision.equals(that.decision) : that.decision != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPostule ^ (idPostule >>> 32));
        result = 31 * result + (dateCreation != null ? dateCreation.hashCode() : 0);
        result = 31 * result + (initiateur != null ? initiateur.hashCode() : 0);
        result = 31 * result + (decision != null ? decision.hashCode() : 0);
        result = 31 * result + (int) (idEcole ^ (idEcole >>> 32));
        result = 31 * result + (int) (idEnseignant ^ (idEnseignant >>> 32));
        return result;
    }

    public EcoleEntity getEcole() {
        return ecole;
    }

    public void setEcole(EcoleEntity ecole) {
        this.ecole = ecole;
    }

    public EnseignantEntity getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(EnseignantEntity enseignant) {
        this.enseignant = enseignant;
    }
}
