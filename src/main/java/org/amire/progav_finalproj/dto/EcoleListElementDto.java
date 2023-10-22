package org.amire.progav_finalproj.dto;

import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.repositories.EnseignantRepository;

import java.util.Date;

public class EcoleListElementDto {
    private final long idEcole;
    private String raisonSociale;
    private String adresseElectronique;
    private String telephone;
    private String siteWeb;
    private String besoin;
    private String competencesRequises;
    private String typeDeContrat;
    private String exigences;
    private Date dateDebutDispo;
    private String remarques;

    private final boolean isFavoris;

    public EcoleListElementDto(EcoleEntity ecole, long idEnseignant) {
        this.idEcole = ecole.getIdEcole();
        this.raisonSociale = ecole.getRaisonSociale();
        this.adresseElectronique = ecole.getAdresseEletronique();
        this.telephone = ecole.getTelephone();
        this.siteWeb = ecole.getSiteWeb();
        this.besoin = ecole.getBesoin();
        this.competencesRequises = ecole.getCompetencesRequises();
        this.typeDeContrat = ecole.getTypeDeContrat();
        this.exigences = ecole.getExigences();
        this.dateDebutDispo = ecole.getDateDebutDispo();
        this.remarques = ecole.getRemarques();
        this.isFavoris = isEcoleInEnseignantFavoris(idEcole, idEnseignant);
    }
    public EcoleListElementDto(long idEcole, long idEnseignant) {
        this.idEcole = idEcole;
        this.isFavoris = isEcoleInEnseignantFavoris(idEcole, idEnseignant);
    }
    private boolean isEcoleInEnseignantFavoris(long idEcole, long idEnseignant) {
        EnseignantEntity enseignant = new EnseignantRepository().getEnseignantById(idEnseignant);
        return enseignant.getFavoris().stream().anyMatch(fav -> fav.getIdEcole() == idEcole);
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
    public String getCompetencesRequises() {
        return competencesRequises;
    }
    public String getTypeDeContrat() {
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
    public boolean isFavoris() {
        return isFavoris;
    }
}
