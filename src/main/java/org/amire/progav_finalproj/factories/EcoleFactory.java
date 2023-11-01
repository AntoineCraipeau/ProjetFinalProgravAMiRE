package org.amire.progav_finalproj.factories;

import jakarta.servlet.http.HttpServletRequest;
import org.amire.progav_finalproj.model.EcoleEntity;

import java.sql.Timestamp;
import java.util.Arrays;

public class EcoleFactory {
    public static EcoleEntity buildEmptyEcole(){
        EcoleEntity ecole = new EcoleEntity();
        ecole.setRaisonSociale("");
        ecole.setCompetencesRequises("");
        ecole.setExigences("");
        ecole.setBesoin("");
        ecole.setDateDebutDispo(new Timestamp(System.currentTimeMillis()));
        ecole.setRemarques("");
        return ecole;
    }

    public static EcoleEntity buildEcoleFromRequest(HttpServletRequest request){
        EcoleEntity ecole = new EcoleEntity();
        ecole.setRaisonSociale(request.getParameter("inputRaisonSociale"));
        ecole.setCompetencesRequises(Arrays.toString(request.getParameterValues("competences")));
        ecole.setExigences(request.getParameter("inputExigences"));
        ecole.setBesoin(request.getParameter("inputBesoin"));
        ecole.setDateDebutDispo(Timestamp.valueOf(request.getParameter("inputDate")+ " 00:00:00"));
        ecole.setRemarques(request.getParameter("inputRemarques"));
        ecole.setTypeDeContrat(Arrays.toString(request.getParameterValues("contrat")));
        ecole.setAdresseEletronique(request.getParameter("inputMail"));
        ecole.setTelephone(request.getParameter("inputTelephone"));
        return ecole;
    }
}
