package org.amire.progav_finalproj.factories;

import jakarta.servlet.http.HttpServletRequest;
import org.amire.progav_finalproj.model.EcoleEntity;

import java.sql.Timestamp;

public class EcoleFactory {
    public static EcoleEntity buildEmptyEcole(){
        EcoleEntity ecole = new EcoleEntity();
        ecole.setRaisonSociale("");
        ecole.setCompetencesRequises("");
        ecole.setExigences("");
        ecole.setBesoin("");
        ecole.setPeriode(new Timestamp(System.currentTimeMillis()));
        ecole.setRemarques("");
        return ecole;
    }

    public static EcoleEntity buildEcoleFromRequest(HttpServletRequest request){
        EcoleEntity ecole = new EcoleEntity();
        ecole.setRaisonSociale(request.getParameter("raisonSociale"));
        ecole.setCompetencesRequises(request.getParameter("competencesRequises"));
        ecole.setExigences(request.getParameter("exigences"));
        ecole.setBesoin(request.getParameter("besoin"));
        ecole.setPeriode(Timestamp.valueOf(request.getParameter("periode")));
        ecole.setRemarques(request.getParameter("remarques"));
        return ecole;
    }
}
