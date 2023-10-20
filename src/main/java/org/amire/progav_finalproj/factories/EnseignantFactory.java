package org.amire.progav_finalproj.factories;

import jakarta.servlet.http.HttpServletRequest;
import org.amire.progav_finalproj.model.EnseignantEntity;

import java.sql.Timestamp;

public class EnseignantFactory {

    public static EnseignantEntity buildEmptyEnseignant(){
        EnseignantEntity enseignant = new EnseignantEntity();
        enseignant.setNom("");
        enseignant.setPrenom("");
        enseignant.setTelephone(0);
        enseignant.setSiteWeb("");
        enseignant.setCompetences("");
        enseignant.setAdresseElectronique("");
        enseignant.setAutresInformations("");
        enseignant.setDisponibilites(new Timestamp(System.currentTimeMillis()));
        enseignant.setExperience("");
        enseignant.setReferencesPro("");
        enseignant.setTitresAcademiques("");
        enseignant.setEvaluations("");
        enseignant.setInteretsDomaines("");
        enseignant.setNiveauxSouhaites("");
        enseignant.setInteretsEcoles("");
        return enseignant;
    }

    public static EnseignantEntity buildEnseignantFromRequest(HttpServletRequest request){
        EnseignantEntity enseignant = new EnseignantEntity();
        enseignant.setNom(request.getParameter("nom"));
        enseignant.setPrenom(request.getParameter("prenom"));
        enseignant.setTelephone(Integer.parseInt(request.getParameter("telephone")));
        enseignant.setSiteWeb(request.getParameter("siteWeb"));
        enseignant.setCompetences(request.getParameter("competences"));
        enseignant.setAdresseElectronique(request.getParameter("adresseElectronique"));
        enseignant.setAutresInformations(request.getParameter("autresInformations"));
        enseignant.setDisponibilites(Timestamp.valueOf(request.getParameter("disponibilites")));
        enseignant.setExperience(request.getParameter("experience"));
        enseignant.setReferencesPro(request.getParameter("referencesPro"));
        enseignant.setTitresAcademiques(request.getParameter("titresAcademiques"));
        enseignant.setEvaluations(request.getParameter("evaluations"));
        enseignant.setInteretsDomaines(request.getParameter("interetsDomaines"));
        enseignant.setNiveauxSouhaites(request.getParameter("niveauxSouhaites"));
        enseignant.setInteretsEcoles(request.getParameter("interetsEcoles"));
        return enseignant;
    }

}
