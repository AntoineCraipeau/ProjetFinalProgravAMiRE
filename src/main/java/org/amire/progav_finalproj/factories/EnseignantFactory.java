package org.amire.progav_finalproj.factories;

import jakarta.servlet.http.HttpServletRequest;
import org.amire.progav_finalproj.model.EnseignantEntity;

import java.sql.Date;
import java.util.Arrays;

public class EnseignantFactory {

    public static EnseignantEntity buildEmptyEnseignant(){
        EnseignantEntity enseignant = new EnseignantEntity();
        enseignant.setNom("");
        enseignant.setPrenom("");
        enseignant.setTelephone("");
        enseignant.setSiteWeb("");
        enseignant.setCompetences("");
        enseignant.setAdresseElectronique("");
        enseignant.setDateDebutDispo(new Date(System.currentTimeMillis()));
        enseignant.setExperience("");
        enseignant.setReferencesPro("");
        enseignant.setTitresAcademiques("");
        enseignant.setEvaluations("");
        enseignant.setInteretsDomaines("");
        enseignant.setNiveauxSouhaites("");
        enseignant.setInteretsEcoles("");
        enseignant.setTypeDeContrat("");
        return enseignant;
    }

    public static EnseignantEntity buildEnseignantFromRequest(HttpServletRequest request) {
        EnseignantEntity enseignant = new EnseignantEntity();
        enseignant.setNom(request.getParameter("inputNom"));
        enseignant.setPrenom(request.getParameter("inputPrenom"));
        enseignant.setTelephone(request.getParameter("inputTelephone"));
        enseignant.setSiteWeb(request.getParameter("inputSiteWeb"));
        enseignant.setCompetences(Arrays.toString(request.getParameterValues("competences")).replace(" ", ""));
        enseignant.setAdresseElectronique(request.getParameter("inputMail"));
        enseignant.setRemarques(request.getParameter("autresInformations")); //!
        try {
            enseignant.setDateDebutDispo(Date.valueOf(request.getParameter("inputDispo")));
        } catch (Exception e) {
            enseignant.setDateDebutDispo(null);
        }
        enseignant.setExperience(request.getParameter("inputExperience"));
        enseignant.setReferencesPro(request.getParameter("inputReference"));
        enseignant.setTitresAcademiques(request.getParameter("inputTitreAca"));
        enseignant.setEvaluations(request.getParameter("inputEvaluations"));
        enseignant.setInteretsDomaines(request.getParameter("inputInteretDomaine"));
        enseignant.setRemarques(request.getParameter("remarques"));
        enseignant.setNiveauxSouhaites(request.getParameter("inputNiveauSouhaite"));
        enseignant.setInteretsEcoles(request.getParameter("inputInteretEcole"));
        enseignant.setTypeDeContrat(Arrays.toString(request.getParameterValues("contrat")).replace(" ", ""));
        enseignant.setLienCv(request.getParameter("inputCV"));
        return enseignant;
    }

}
