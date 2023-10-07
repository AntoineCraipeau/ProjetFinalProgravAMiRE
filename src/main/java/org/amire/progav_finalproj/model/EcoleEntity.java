package org.amire.progav_finalproj.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "ecole", schema = "db_prograv_final", catalog = "")
public class EcoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ecole", nullable = false)
    private long idEcole;
    @Basic
    @Column(name = "Raison_sociale", nullable = false, length = 255)
    private String raisonSociale;
    @Basic
    @Column(name = "Besoin", nullable = false, length = 255)
    private String besoin;
    @Basic
    @Column(name = "Competences_requises", nullable = false, length = 255)
    private String competencesRequises;
    @Basic
    @Column(name = "Exigences", nullable = false, length = 255)
    private String exigences;
    @Basic
    @Column(name = "Periode", nullable = false)
    private Timestamp periode;
    @Basic
    @Column(name = "Remarques", nullable = true, length = 255)
    private String remarques;
    @OneToMany(mappedBy = "ecoleByIdEcole")
    private Collection<EcolesFavorisEntity> ecolesFavorisesByIdEcole;
    @OneToMany(mappedBy = "ecoleByIdEcole")
    private Collection<PostuleEntity> postulesByIdEcole;

    public long getIdEcole() {
        return idEcole;
    }

    public void setIdEcole(long idEcole) {
        this.idEcole = idEcole;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getBesoin() {
        return besoin;
    }

    public void setBesoin(String besoin) {
        this.besoin = besoin;
    }

    public String getCompetencesRequises() {
        return competencesRequises;
    }

    public void setCompetencesRequises(String competencesRequises) {
        this.competencesRequises = competencesRequises;
    }

    public String getExigences() {
        return exigences;
    }

    public void setExigences(String exigences) {
        this.exigences = exigences;
    }

    public Timestamp getPeriode() {
        return periode;
    }

    public void setPeriode(Timestamp periode) {
        this.periode = periode;
    }

    public String getRemarques() {
        return remarques;
    }

    public void setRemarques(String remarques) {
        this.remarques = remarques;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EcoleEntity that = (EcoleEntity) o;

        if (idEcole != that.idEcole) return false;
        if (raisonSociale != null ? !raisonSociale.equals(that.raisonSociale) : that.raisonSociale != null)
            return false;
        if (besoin != null ? !besoin.equals(that.besoin) : that.besoin != null) return false;
        if (competencesRequises != null ? !competencesRequises.equals(that.competencesRequises) : that.competencesRequises != null)
            return false;
        if (exigences != null ? !exigences.equals(that.exigences) : that.exigences != null) return false;
        if (periode != null ? !periode.equals(that.periode) : that.periode != null) return false;
        if (remarques != null ? !remarques.equals(that.remarques) : that.remarques != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idEcole ^ (idEcole >>> 32));
        result = 31 * result + (raisonSociale != null ? raisonSociale.hashCode() : 0);
        result = 31 * result + (besoin != null ? besoin.hashCode() : 0);
        result = 31 * result + (competencesRequises != null ? competencesRequises.hashCode() : 0);
        result = 31 * result + (exigences != null ? exigences.hashCode() : 0);
        result = 31 * result + (periode != null ? periode.hashCode() : 0);
        result = 31 * result + (remarques != null ? remarques.hashCode() : 0);
        return result;
    }

    public Collection<EcolesFavorisEntity> getEcolesFavorisesByIdEcole() {
        return ecolesFavorisesByIdEcole;
    }

    public void setEcolesFavorisesByIdEcole(Collection<EcolesFavorisEntity> ecolesFavorisesByIdEcole) {
        this.ecolesFavorisesByIdEcole = ecolesFavorisesByIdEcole;
    }

    public Collection<PostuleEntity> getPostulesByIdEcole() {
        return postulesByIdEcole;
    }

    public void setPostulesByIdEcole(Collection<PostuleEntity> postulesByIdEcole) {
        this.postulesByIdEcole = postulesByIdEcole;
    }
}
