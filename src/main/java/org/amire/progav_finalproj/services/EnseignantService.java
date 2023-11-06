package org.amire.progav_finalproj.services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.amire.progav_finalproj.Controleurs;
import org.amire.progav_finalproj.UserBean;
import org.amire.progav_finalproj.factories.EnseignantFactory;
import org.amire.progav_finalproj.factories.PostuleFactory;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.model.PostuleEntity;
import org.amire.progav_finalproj.repositories.*;
import org.amire.progav_finalproj.utils.ActionTypes;
import org.amire.progav_finalproj.utils.ActionTypesUtils;

import java.io.IOException;

@Stateless
public class EnseignantService {

    @EJB
    private UserRepository userRepository;
    @EJB
    private EnseignantRepository enseignantRepository;
    @EJB
    private FavorisEnseignantRepository favorisEnseignantRepository;
    @EJB
    private PostuleRepository postuleRepository;
    @EJB
    private EcoleRepository ecoleRepository;
    @EJB
    private AuthService authService;

    public void handleEnseignantRequest(HttpServletRequest request, UserBean unUtilisateur){
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        // Enseignant ayant envoyé la requête
        EnseignantEntity enseignant = userRepository.getUserById(unUtilisateur.getIdUserinfo()).getEnseignant();
        long idEnseignant = enseignant.getIdEnseignant();
        // Ecole ciblée par la requête (si applicable)
        long idEcole = request.getParameter("idEcole") != null ? Long.parseLong(request.getParameter("idEcole")) : 0;
        EcoleEntity ecole = idEcole != 0 ? ecoleRepository.getEcoleById(idEcole) : null;
        PostuleEntity postule;

        switch (action){
            case AjoutFavorisEnseignant:
                if(ecole == null){
                    request.setAttribute("messageErreur", "L'école n'existe pas");
                    break;
                }
                favorisEnseignantRepository.addCandidatsFavoris(enseignant, ecole);
                break;
            case RetraitFavorisEnseignant:
                favorisEnseignantRepository.removeCandidatsFavorisByOwnersId(idEnseignant, idEcole);
                break;
            case AjoutPostulationEnseignant:
                postuleRepository.addPostule(PostuleFactory.buildPostule(enseignant, ecole, "enseignant"));
                break;
            case AccepterPostulationEnseignant:
                postule = postuleRepository.getPostuleById(request.getParameter("idPostule") != null ? Long.parseLong(request.getParameter("idPostule")) : 0);
                postule.setDecision("Accepté");
                postuleRepository.editPostule(postule);
                break;
            case RefuserPostulationEnseignant:
                postule = postuleRepository.getPostuleById(request.getParameter("idPostule") != null ? Long.parseLong(request.getParameter("idPostule")) : 0);
                postule.setDecision("Refusé");
                postuleRepository.editPostule(postule);
                break;
            case ModifPostulationEnseignant:
                postule = postuleRepository.getPostuleById(request.getParameter("idPostule") != null ? Long.parseLong(request.getParameter("idPostule")) : 0);
                postule.setDecision(request.getParameter("decision"));
                postuleRepository.editPostule(postule);
                // Y'a besoin de changer autre chose que la décision ?
                break;
            case RetraitPostulationEnseignant:
                postuleRepository.removePostuleById(request.getParameter("idPostule") != null ? Long.parseLong(request.getParameter("idPostule")) : 0);
                break;
            case ModifierProfil:
                try {
                    EnseignantEntity enseignantFromRequest = EnseignantFactory.buildEnseignantFromRequest(request);
                    enseignantFromRequest.setIdEnseignant(idEnseignant);
                    enseignantRepository.editEnseignant(enseignantFromRequest);
                    request.setAttribute("messageSucces", Controleurs.MESSAGE_SUCCES_MODIFICATION_COMPTE);
                } catch (Exception e) {
                    request.setAttribute("messageErreur", Controleurs.MESSAGE_ERREUR_PROFIL_MODIFICATION_ECHEC);
                }
                break;
            case ModifierMdp:
                authService.modifierMotDePasse(request, unUtilisateur);
                break;
        }
    }

    public void handleEnseignantRedirection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        if(action == ActionTypes.EnseignantVersDashboard)
            request.getRequestDispatcher("WEB-INF/EnseignantPages/tableauBordEnseignant.jsp").forward(request, response);
        else if (action == ActionTypes.EnseignantVersProfil || action == ActionTypes.ModifierProfil || action == ActionTypes.ModifierMdp)
            request.getRequestDispatcher("WEB-INF/EnseignantPages/profil_enseignant.jsp").forward(request, response);
        else if (action == ActionTypes.EnseignantVersMatch || action == ActionTypes.Recherche)
            request.getRequestDispatcher("WEB-INF/EnseignantPages/matchEnseignant.jsp").forward(request, response);
        else {
            request.getRequestDispatcher("WEB-INF/EnseignantPages/tableauBordEnseignant.jsp").forward(request, response);
        }
    }

}