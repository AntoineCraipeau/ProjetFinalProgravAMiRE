package org.amire.progav_finalproj.dto;

import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.repositories.JpaUserRepository;

import java.text.SimpleDateFormat;

public class EnseignantApiDto {
    private final long idEnseignant;
    private final long idUser;
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
    private String dateDebutDispo;
    private ContratSetDto typeDeContrat;
    private String contratText;
    private String titresAcademiques;
    private String remarques;
    private String referencesPro;
    private String lienCv;

    public EnseignantApiDto(EnseignantEntity enseignant) {
        this.idEnseignant = enseignant.getIdEnseignant();
        this.idUser = new JpaUserRepository().resolveUserIdFromEnseignantId(idEnseignant);
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
        this.dateDebutDispo = new SimpleDateFormat("dd/MM/yyyy").format(enseignant.getDateDebutDispo());
        this.typeDeContrat = new ContratSetDto(enseignant.getTypeDeContrat());
        this.contratText = enseignant.getTypeDeContrat() == null ? "" : enseignant.getTypeDeContrat().replace("[","").replace("]", "");
        this.titresAcademiques = enseignant.getTitresAcademiques();
        this.remarques = enseignant.getRemarques();
        this.referencesPro = enseignant.getReferencesPro();
        this.lienCv = enseignant.getLienCv();
    }

    public long getIdEnseignant() {
        return idEnseignant;
    }
    public long getIdUser() {
        return idUser;
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
    public String getDateDebutDispo() {
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
}
