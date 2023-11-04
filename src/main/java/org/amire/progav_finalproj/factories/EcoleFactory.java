package org.amire.progav_finalproj.factories;

import jakarta.servlet.http.HttpServletRequest;
import org.amire.progav_finalproj.model.EcoleEntity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class EcoleFactory {
    public static EcoleEntity buildEmptyEcole(){
        EcoleEntity ecole = new EcoleEntity();
        ecole.setRaisonSociale("");
        ecole.setCompetencesRequises("");
        ecole.setExigences("");
        ecole.setBesoin("");
        ecole.setDateDebutDispo(new Date(System.currentTimeMillis()));
        ecole.setRemarques("");
        ecole.setSiteWeb("");
        return ecole;
    }

    public static EcoleEntity buildEcoleFromRequest(HttpServletRequest request){
        EcoleEntity ecole = new EcoleEntity();
        ecole.setRaisonSociale(request.getParameter("inputRaisonSociale"));
        ecole.setCompetencesRequises(Arrays.toString(request.getParameterValues("competences")).replace(" ", ""));
        ecole.setExigences(request.getParameter("inputExigences"));
        ecole.setBesoin(request.getParameter("inputBesoin"));
        try {
            ecole.setDateDebutDispo(Date.valueOf(request.getParameter("inputDate")+ " 00:00:00"));
        } catch (IllegalArgumentException e) {
            ecole.setDateDebutDispo(null);
        }
        ecole.setRemarques(request.getParameter("inputRemarques"));
        ecole.setTypeDeContrat(Arrays.toString(request.getParameterValues("contrat")).replace(" ", ""));
        ecole.setAdresseEletronique(request.getParameter("inputMail"));
        ecole.setTelephone(request.getParameter("inputTelephone"));
        ecole.setSiteWeb(request.getParameter("inputSiteWeb"));
        return ecole;
    }
}
