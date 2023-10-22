package org.amire.progav_finalproj.dto;

import org.amire.progav_finalproj.model.EcoleEntity;

import java.util.Date;

public class EcoleProfileInfoDto {

    private final long idEcole;
    private String raisonSociale;
    private String adresseElectronique;
    private String telephone;
    private String siteWeb;
    private String besoin;
    private CompetenceSetDto competencesRequises;
    private ContratSetDto typeDeContrat;
    private String exigences;
    private Date dateDebutDispo;
    private String remarques;

    public EcoleProfileInfoDto(EcoleEntity ecole) {
        this.idEcole = ecole.getIdEcole();
        this.raisonSociale = ecole.getRaisonSociale();
        this.adresseElectronique = ecole.getAdresseEletronique();
        this.telephone = ecole.getTelephone();
        this.siteWeb = ecole.getSiteWeb();
        this.besoin = ecole.getBesoin();
        this.competencesRequises = new CompetenceSetDto(ecole.getCompetencesRequises());
        this.typeDeContrat = new ContratSetDto(ecole.getTypeDeContrat());
        this.exigences = ecole.getExigences();
        this.dateDebutDispo = ecole.getDateDebutDispo();
        this.remarques = ecole.getRemarques();
    }

    public long getIdEcole() {
        return idEcole;
    }
    public String getRaisonSociale() {
        return raisonSociale;
    }
    public String getAdresseElectronique() {
        return adresseElectronique;
    }
    public String getTelephone() {
        return telephone;
    }
    public String getSiteWeb() {
        return siteWeb;
    }
    public String getBesoin() {
        return besoin;
    }
    public CompetenceSetDto getCompetencesRequises() {
        return competencesRequises;
    }
    public ContratSetDto getTypeDeContrat() {
        return typeDeContrat;
    }
    public String getExigences() {
        return exigences;
    }
    public Date getDateDebutDispo() {
        return dateDebutDispo;
    }
    public String getRemarques() {
        return remarques;
    }
}
