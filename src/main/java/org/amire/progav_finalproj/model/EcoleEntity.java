package org.amire.progav_finalproj.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "ecole", schema = "db_prograv_final")
public class EcoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_ecole", nullable = false)
    private long idEcole;
    @Basic
    @Column(name = "Raison_sociale", nullable = true, length = 255)
    private String raisonSociale;
    @Basic
    @Column(name = "Adresse_eletronique", nullable = true, length = 255)
    private String adresseEletronique;
    @Basic
    @Column(name = "Telephone", nullable = true, length = 255)
    private String telephone;
    @Basic
    @Column(name = "Site_Web", nullable = true, length = 255)
    private String siteWeb;
    @Basic
    @Column(name = "Besoin", nullable = true, length = 255)
    private String besoin;
    @Basic
    @Column(name = "Competences_requises", nullable = true, length = 512)
    private String competencesRequises;
    @Basic
    @Column(name = "Type_de_contrat", nullable = true, length = 512)
    private String typeDeContrat;
    @Basic
    @Column(name = "Exigences", nullable = true, length = 255)
    private String exigences;
    @Basic
    @Column(name = "Date_Debut_Dispo", nullable = true)
    private Date dateDebutDispo;
    @Basic
    @Column(name = "Remarques", nullable = true, length = 255)
    private String remarques;

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

    public String getAdresseEletronique() {
        return adresseEletronique;
    }

    public void setAdresseEletronique(String adresseEletronique) {
        this.adresseEletronique = adresseEletronique;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
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

    public String getTypeDeContrat() {
        return typeDeContrat;
    }

    public void setTypeDeContrat(String typeDeContrat) {
        this.typeDeContrat = typeDeContrat;
    }

    public String getExigences() {
        return exigences;
    }

    public void setExigences(String exigences) {
        this.exigences = exigences;
    }

    public Date getDateDebutDispo() {
        return dateDebutDispo;
    }

    public void setDateDebutDispo(Date dateDebutDispo) {
        this.dateDebutDispo = dateDebutDispo;
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
        if (adresseEletronique != null ? !adresseEletronique.equals(that.adresseEletronique) : that.adresseEletronique != null)
            return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        if (siteWeb != null ? !siteWeb.equals(that.siteWeb) : that.siteWeb != null) return false;
        if (besoin != null ? !besoin.equals(that.besoin) : that.besoin != null) return false;
        if (competencesRequises != null ? !competencesRequises.equals(that.competencesRequises) : that.competencesRequises != null)
            return false;
        if (typeDeContrat != null ? !typeDeContrat.equals(that.typeDeContrat) : that.typeDeContrat != null)
            return false;
        if (exigences != null ? !exigences.equals(that.exigences) : that.exigences != null) return false;
        if (dateDebutDispo != null ? !dateDebutDispo.equals(that.dateDebutDispo) : that.dateDebutDispo != null)
            return false;
        if (remarques != null ? !remarques.equals(that.remarques) : that.remarques != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idEcole ^ (idEcole >>> 32));
        result = 31 * result + (raisonSociale != null ? raisonSociale.hashCode() : 0);
        result = 31 * result + (adresseEletronique != null ? adresseEletronique.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (siteWeb != null ? siteWeb.hashCode() : 0);
        result = 31 * result + (besoin != null ? besoin.hashCode() : 0);
        result = 31 * result + (competencesRequises != null ? competencesRequises.hashCode() : 0);
        result = 31 * result + (typeDeContrat != null ? typeDeContrat.hashCode() : 0);
        result = 31 * result + (exigences != null ? exigences.hashCode() : 0);
        result = 31 * result + (dateDebutDispo != null ? dateDebutDispo.hashCode() : 0);
        result = 31 * result + (remarques != null ? remarques.hashCode() : 0);
        return result;
    }
}
