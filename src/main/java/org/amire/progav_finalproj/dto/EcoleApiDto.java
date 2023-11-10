package org.amire.progav_finalproj.dto;

import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.repositories.UserRepository;

import java.text.SimpleDateFormat;

public class EcoleApiDto {

    private final long idEcole;
    private final long idUser;
    private String raisonSociale;
    private String adresseElectronique;
    private String telephone;
    private String siteWeb;
    private String besoin;
    private CompetenceSetDto competencesRequises;
    private String competenceText;
    private ContratSetDto typeDeContrat;
    private String contratText;
    private String exigences;
    private String dateDebutDispo;
    private String remarques;

    public EcoleApiDto(EcoleEntity ecole) {
        this.idEcole = ecole.getIdEcole();
        this.idUser = new UserRepository().resolveUserIdFromEcoleId(idEcole);
        this.raisonSociale = ecole.getRaisonSociale();
        this.adresseElectronique = ecole.getAdresseEletronique();
        this.telephone = ecole.getTelephone();
        this.siteWeb = ecole.getSiteWeb();
        this.besoin = ecole.getBesoin();
        this.competencesRequises = new CompetenceSetDto(ecole.getCompetencesRequises());
        this.competenceText = ecole.getCompetencesRequises() == null ? "" : ecole.getCompetencesRequises().replace("[","").replace("]", "");
        this.typeDeContrat = new ContratSetDto(ecole.getTypeDeContrat());
        this.contratText = ecole.getTypeDeContrat() == null ? "" : ecole.getTypeDeContrat().replace("[","").replace("]", "");
        this.exigences = ecole.getExigences();
        this.dateDebutDispo = new SimpleDateFormat("dd/MM/yyyy").format(ecole.getDateDebutDispo());
        this.remarques = ecole.getRemarques();
    }

    public long getIdEcole() {
        return idEcole;
    }
    public long getIdUser() {
        return idUser;
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
    public String getCompetenceText() {
        return competenceText;
    }
    public ContratSetDto getTypeDeContrat() {
        return typeDeContrat;
    }
    public String getContratText() {
        return contratText;
    }
    public String getExigences() {
        return exigences;
    }
    public String getDateDebutDispo() {
        return dateDebutDispo;
    }
    public String getRemarques() {
        return remarques;
    }
}
