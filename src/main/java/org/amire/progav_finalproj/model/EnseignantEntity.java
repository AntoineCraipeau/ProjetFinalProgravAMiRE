package org.amire.progav_finalproj.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "enseignant", schema = "db_prograv_final")
public class EnseignantEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_enseignant", nullable = false)
    private long idEnseignant;
    @Basic
    @Column(name = "Nom", nullable = false, length = 255)
    private String nom;
    @Basic
    @Column(name = "Prenom", nullable = false, length = 255)
    private String prenom;
    @Basic
    @Column(name = "Experience", nullable = false, length = 255)
    private String experience;
    @Basic
    @Column(name = "Evaluations", nullable = false, length = 255)
    private String evaluations;
    @Basic
    @Column(name = "Competences", nullable = false, length = 255)
    private String competences;
    @Basic
    @Column(name = "Interets_domaines", nullable = true, length = 255)
    private String interetsDomaines;
    @Basic
    @Column(name = "Interets_ecoles", nullable = false, length = 255)
    private String interetsEcoles;
    @Basic
    @Column(name = "Niveaux_souhaites", nullable = false, length = 255)
    private String niveauxSouhaites;
    @Basic
    @Column(name = "Adresse_electronique", nullable = false, length = 255)
    private String adresseElectronique;
    @Basic
    @Column(name = "Telephone", nullable = false)
    private long telephone;
    @Basic
    @Column(name = "Site_Web", nullable = false, length = 255)
    private String siteWeb;
    @Basic
    @Column(name = "Disponibilites", nullable = false)
    private Timestamp disponibilites;
    @Basic
    @Column(name = "Type_de_contrat", nullable = false, length = 255)
    private String typeDeContrat;
    @Basic
    @Column(name = "Titres_academiques", nullable = false, length = 255)
    private String titresAcademiques;
    @Basic
    @Column(name = "Autres_informations", nullable = true, length = 255)
    private String autresInformations;
    @Basic
    @Column(name = "References_pro", nullable = false, length = 255)
    private String referencesPro;
    @OneToMany(mappedBy = "enseignantByIdEnseignant")
    private Collection<CandidatsFavorisEntity> candidatsFavorisesByIdEnseignant;
    @OneToMany(mappedBy = "enseignantByIdEnseignant")
    private Collection<PostuleEntity> postulesByIdEnseignant;

    public long getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(long idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(String evaluations) {
        this.evaluations = evaluations;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public String getInteretsDomaines() {
        return interetsDomaines;
    }

    public void setInteretsDomaines(String interetsDomaines) {
        this.interetsDomaines = interetsDomaines;
    }

    public String getInteretsEcoles() {
        return interetsEcoles;
    }

    public void setInteretsEcoles(String interetsEcoles) {
        this.interetsEcoles = interetsEcoles;
    }

    public String getNiveauxSouhaites() {
        return niveauxSouhaites;
    }

    public void setNiveauxSouhaites(String niveauxSouhaites) {
        this.niveauxSouhaites = niveauxSouhaites;
    }

    public String getAdresseElectronique() {
        return adresseElectronique;
    }

    public void setAdresseElectronique(String adresseElectronique) {
        this.adresseElectronique = adresseElectronique;
    }

    public long getTelephone() {
        return telephone;
    }

    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public Timestamp getDisponibilites() {
        return disponibilites;
    }

    public void setDisponibilites(Timestamp disponibilites) {
        this.disponibilites = disponibilites;
    }

    public String getTypeDeContrat() {
        return typeDeContrat;
    }

    public void setTypeDeContrat(String typeDeContrat) {
        this.typeDeContrat = typeDeContrat;
    }

    public String getTitresAcademiques() {
        return titresAcademiques;
    }

    public void setTitresAcademiques(String titresAcademiques) {
        this.titresAcademiques = titresAcademiques;
    }

    public String getAutresInformations() {
        return autresInformations;
    }

    public void setAutresInformations(String autresInformations) {
        this.autresInformations = autresInformations;
    }

    public String getReferencesPro() {
        return referencesPro;
    }

    public void setReferencesPro(String referencesPro) {
        this.referencesPro = referencesPro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnseignantEntity that = (EnseignantEntity) o;

        if (idEnseignant != that.idEnseignant) return false;
        if (telephone != that.telephone) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (experience != null ? !experience.equals(that.experience) : that.experience != null) return false;
        if (evaluations != null ? !evaluations.equals(that.evaluations) : that.evaluations != null) return false;
        if (competences != null ? !competences.equals(that.competences) : that.competences != null) return false;
        if (interetsDomaines != null ? !interetsDomaines.equals(that.interetsDomaines) : that.interetsDomaines != null)
            return false;
        if (interetsEcoles != null ? !interetsEcoles.equals(that.interetsEcoles) : that.interetsEcoles != null)
            return false;
        if (niveauxSouhaites != null ? !niveauxSouhaites.equals(that.niveauxSouhaites) : that.niveauxSouhaites != null)
            return false;
        if (adresseElectronique != null ? !adresseElectronique.equals(that.adresseElectronique) : that.adresseElectronique != null)
            return false;
        if (siteWeb != null ? !siteWeb.equals(that.siteWeb) : that.siteWeb != null) return false;
        if (disponibilites != null ? !disponibilites.equals(that.disponibilites) : that.disponibilites != null)
            return false;
        if (typeDeContrat != null ? !typeDeContrat.equals(that.typeDeContrat) : that.typeDeContrat != null)
            return false;
        if (titresAcademiques != null ? !titresAcademiques.equals(that.titresAcademiques) : that.titresAcademiques != null)
            return false;
        if (autresInformations != null ? !autresInformations.equals(that.autresInformations) : that.autresInformations != null)
            return false;
        if (referencesPro != null ? !referencesPro.equals(that.referencesPro) : that.referencesPro != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idEnseignant ^ (idEnseignant >>> 32));
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (evaluations != null ? evaluations.hashCode() : 0);
        result = 31 * result + (competences != null ? competences.hashCode() : 0);
        result = 31 * result + (interetsDomaines != null ? interetsDomaines.hashCode() : 0);
        result = 31 * result + (interetsEcoles != null ? interetsEcoles.hashCode() : 0);
        result = 31 * result + (niveauxSouhaites != null ? niveauxSouhaites.hashCode() : 0);
        result = 31 * result + (adresseElectronique != null ? adresseElectronique.hashCode() : 0);
        result = 31 * result + (int) (telephone ^ (telephone >>> 32));
        result = 31 * result + (siteWeb != null ? siteWeb.hashCode() : 0);
        result = 31 * result + (disponibilites != null ? disponibilites.hashCode() : 0);
        result = 31 * result + (typeDeContrat != null ? typeDeContrat.hashCode() : 0);
        result = 31 * result + (titresAcademiques != null ? titresAcademiques.hashCode() : 0);
        result = 31 * result + (autresInformations != null ? autresInformations.hashCode() : 0);
        result = 31 * result + (referencesPro != null ? referencesPro.hashCode() : 0);
        return result;
    }

    public Collection<CandidatsFavorisEntity> getCandidatsFavorisesByIdEnseignant() {
        return candidatsFavorisesByIdEnseignant;
    }

    public void setCandidatsFavorisesByIdEnseignant(Collection<CandidatsFavorisEntity> candidatsFavorisesByIdEnseignant) {
        this.candidatsFavorisesByIdEnseignant = candidatsFavorisesByIdEnseignant;
    }

    public Collection<PostuleEntity> getPostulesByIdEnseignant() {
        return postulesByIdEnseignant;
    }

    public void setPostulesByIdEnseignant(Collection<PostuleEntity> postulesByIdEnseignant) {
        this.postulesByIdEnseignant = postulesByIdEnseignant;
    }
}
