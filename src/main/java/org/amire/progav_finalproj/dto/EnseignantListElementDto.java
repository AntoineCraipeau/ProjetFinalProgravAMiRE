package org.amire.progav_finalproj.dto;

import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.repositories.FavorisEcoleRepository;

import java.util.Date;

public class EnseignantListElementDto {
    private final long idEnseignant;
    private String nom;
    private String prenom;
    private String adresseElectronique;
    private String telephone;
    private String siteWeb;
    private String experience;
    private String evaluations;
    private CompetenceSetDto competences;
    private String competenceText;
    private String interetsDomaines;
    private String interetsEcoles;
    private String niveauxSouhaites;
    private Date dateDebutDispo;
    private ContratSetDto typeDeContrat;
    private String contratText;
    private String titresAcademiques;
    private String remarques;
    private String referencesPro;
    private String lienCv;
    private final boolean isFavoris;

    public EnseignantListElementDto(EnseignantEntity enseignant, long idEcole) {
        this.idEnseignant = enseignant.getIdEnseignant();
        this.nom = enseignant.getNom();
        this.prenom = enseignant.getPrenom();
        this.adresseElectronique = enseignant.getAdresseElectronique();
        this.telephone = enseignant.getTelephone();
        this.siteWeb = enseignant.getSiteWeb();
        this.experience = enseignant.getExperience();
        this.evaluations = enseignant.getEvaluations();
        this.competences = new CompetenceSetDto(enseignant.getCompetences());
        this.competenceText = enseignant.getCompetences() == null ? "" : enseignant.getCompetences().replace("[","").replace("]", "");
        this.interetsDomaines = enseignant.getInteretsDomaines();
        this.interetsEcoles = enseignant.getInteretsEcoles();
        this.niveauxSouhaites = enseignant.getNiveauxSouhaites();
        this.dateDebutDispo = enseignant.getDateDebutDispo();
        this.typeDeContrat = new ContratSetDto(enseignant.getTypeDeContrat());
        this.contratText = enseignant.getTypeDeContrat() == null ? "" : enseignant.getTypeDeContrat().replace("[","").replace("]", "");
        this.titresAcademiques = enseignant.getTitresAcademiques();
        this.remarques = enseignant.getRemarques();
        this.referencesPro = enseignant.getReferencesPro();
        this.lienCv = enseignant.getLienCv();
        this.isFavoris = isEnseignantInEcoleFavoris(idEnseignant, idEcole);
    }
    private boolean isEnseignantInEcoleFavoris(long idEnseignant, long idEcole) {
        return new FavorisEcoleRepository().getFavorisEcoleByOwnersId(idEcole, idEnseignant) != null;
    }

    public long getIdEnseignant() {
        return idEnseignant;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
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
    public String getExperience() {
        return experience;
    }
    public String getEvaluations() {
        return evaluations;
    }
    public CompetenceSetDto getCompetences() {
        return competences;
    }
    public String getCompetenceText() {
        return competenceText;
    }
    public String getInteretsDomaines() {
        return interetsDomaines;
    }
    public String getInteretsEcoles() {
        return interetsEcoles;
    }
    public String getNiveauxSouhaites() {
        return niveauxSouhaites;
    }
    public Date getDateDebutDispo() {
        return dateDebutDispo;
    }
    public ContratSetDto getTypeDeContrat() {
        return typeDeContrat;
    }
    public String getContratText() {
        return contratText;
    }
    public String getTitresAcademiques() {
        return titresAcademiques;
    }
    public String getRemarques() {
        return remarques;
    }
    public String getReferencesPro() {
        return referencesPro;
    }
    public String getLienCv() {
        return lienCv;
    }
    public boolean getIsFavoris() {
        return isFavoris;
    }
}
