package org.amire.progav_finalproj.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ecoles_favoris", schema = "db_prograv_final")
public class EcolesFavorisEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ecoles_favories", nullable = false)
    private long idEcolesFavories;
    @Basic
    @Column(name = "id_ecole", nullable = false, insertable = false, updatable = false)
    private long idEcole;
    @Basic
    @Column(name = "id_enseignant", nullable = false, insertable = false, updatable = false)
    private long idEnseignant;
    @ManyToOne
    @JoinColumn(name = "id_ecole", referencedColumnName = "id_ecole", nullable = false)
    private EcoleEntity ecoleByIdEcole;
    @ManyToOne
    @JoinColumn(name = "id_enseignant", referencedColumnName = "id_enseignant", nullable = false)
    private EnseignantEntity enseignantByIdEnseignant;

    public long getIdEcolesFavories() {
        return idEcolesFavories;
    }

    public void setIdEcolesFavories(long idEcolesFavories) {
        this.idEcolesFavories = idEcolesFavories;
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

        EcolesFavorisEntity that = (EcolesFavorisEntity) o;

        if (idEcolesFavories != that.idEcolesFavories) return false;
        if (idEcole != that.idEcole) return false;
        if (idEnseignant != that.idEnseignant) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idEcolesFavories ^ (idEcolesFavories >>> 32));
        result = 31 * result + (int) (idEcole ^ (idEcole >>> 32));
        result = 31 * result + (int) (idEnseignant ^ (idEnseignant >>> 32));
        return result;
    }

    public EcoleEntity getEcoleByIdEcole() {
        return ecoleByIdEcole;
    }

    public void setEcoleByIdEcole(EcoleEntity ecoleByIdEcole) {
        this.ecoleByIdEcole = ecoleByIdEcole;
    }

    public EnseignantEntity getEnseignantByIdEnseignant() {
        return enseignantByIdEnseignant;
    }

    public void setEnseignantByIdEnseignant(EnseignantEntity enseignantByIdEnseignant) {
        this.enseignantByIdEnseignant = enseignantByIdEnseignant;
    }
}
