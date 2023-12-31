package org.amire.progav_finalproj.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favoris_enseignant", schema = "db_prograv_final")
public class FavorisEnseignantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_candidats_favoris", nullable = false)
    private long idCandidatsFavoris;
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

    public long getIdCandidatsFavoris() {
        return idCandidatsFavoris;
    }

    public void setIdCandidatsFavoris(long idCandidatsFavoris) {
        this.idCandidatsFavoris = idCandidatsFavoris;
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

        FavorisEnseignantEntity that = (FavorisEnseignantEntity) o;

        if (idCandidatsFavoris != that.idCandidatsFavoris) return false;
        if (idEcole != that.idEcole) return false;
        if (idEnseignant != that.idEnseignant) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idCandidatsFavoris ^ (idCandidatsFavoris >>> 32));
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
