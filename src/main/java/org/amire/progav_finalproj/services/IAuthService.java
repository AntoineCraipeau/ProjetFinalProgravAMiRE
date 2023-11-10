package org.amire.progav_finalproj.services;

import jakarta.servlet.http.HttpServletRequest;
import org.amire.progav_finalproj.UserBean;

public interface IAuthService {
    public boolean verifierInfosConnexion(HttpServletRequest request);
    public void connexion(HttpServletRequest request, UserBean unUtilisateur);
    public void decoderSession(HttpServletRequest request, UserBean unUtilisateur);
    public void deconnexion(HttpServletRequest request, UserBean unUtilisateur);
    public void modifierMotDePasse (HttpServletRequest request, UserBean unUtilisateur);
}
