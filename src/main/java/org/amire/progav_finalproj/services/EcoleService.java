package org.amire.progav_finalproj.services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.amire.progav_finalproj.Controleurs;
import org.amire.progav_finalproj.UserBean;
import org.amire.progav_finalproj.factories.EcoleFactory;
import org.amire.progav_finalproj.factories.PostuleFactory;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.model.PostuleEntity;
import org.amire.progav_finalproj.repositories.*;
import org.amire.progav_finalproj.utils.ActionTypes;
import org.amire.progav_finalproj.utils.ActionTypesUtils;

import java.io.IOException;

@Stateless
public class EcoleService {

    @EJB
    private UserRepository userRepository;
    @EJB
    private EnseignantRepository enseignantRepository;
    @EJB
    private FavorisEcoleRepository favorisEcoleRepository;
    @EJB
    private PostuleRepository postuleRepository;
    @EJB
    private EcoleRepository ecoleRepository;
    @EJB
    private AuthService authService;


    public void handleEcoleRequest(HttpServletRequest request, UserBean unUtilisateur){
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        //Ecole ayant envoyé la requête
        EcoleEntity ecole = userRepository.getUserById(unUtilisateur.getIdUserinfo()).getEcole();
        long idEcole = ecole.getIdEcole();
        //Enseignant ciblé par la requête (si applicable)
        long idEnseignant = request.getParameter("idEnseignant") != null ? Long.parseLong(request.getParameter("idEnseignant")) : 0;
        EnseignantEntity enseignant = idEnseignant != 0 ? enseignantRepository.getEnseignantById(idEnseignant) : null;
        PostuleEntity postule;

        switch (action) {
            case AjoutFavorisEcole:
                if(enseignant == null){
                    request.setAttribute("messageErreur", "L'enseignant n'existe pas");
                    break;
                }
                favorisEcoleRepository.addFavorisEcole(ecole, enseignant);
                break;
            case RetraitFavorisEcole:
                favorisEcoleRepository.removeFavorisEcoleByOwnersIds(idEcole, idEnseignant);
                break;
            case AjoutPostulationEcole:
                postuleRepository.addPostule(PostuleFactory.buildPostule(enseignant, ecole, "ecole"));
                break;
            case AccepterPostulationEcole:
                postule = postuleRepository.getPostuleById(request.getParameter("idPostule") != null ? Long.parseLong(request.getParameter("idPostule")) : 0);
                postule.setDecision("Accepté");
                postuleRepository.editPostule(postule);
                break;
            case RefuserPostulationEcole:
                postule = postuleRepository.getPostuleById(request.getParameter("idPostule") != null ? Long.parseLong(request.getParameter("idPostule")) : 0);
                postule.setDecision("Refusé");
                postuleRepository.editPostule(postule);
                break;
            case ModifPostulationEcole:
                postule = postuleRepository.getPostuleById(request.getParameter("idPostule") != null ? Long.parseLong(request.getParameter("idPostule")) : 0);
                postule.setDecision(request.getParameter("decision"));
                postuleRepository.editPostule(postule);
                // Y'a besoin de changer autre chose que la décision ?
                break;
            case RetraitPostulationEcole:
                postuleRepository.removePostuleById(request.getParameter("idPostule") != null ? Long.parseLong(request.getParameter("idPostule")) : 0);
                break;
            case ModifierProfil:
                try {
                    EcoleEntity ecoleFromRequest = EcoleFactory.buildEcoleFromRequest(request);
                    ecoleFromRequest.setIdEcole(idEcole);
                    ecoleRepository.editEcole(ecoleFromRequest);
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

    public void handleEcoleRedirection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        if(action == ActionTypes.EcoleVersDashboard)
            request.getRequestDispatcher("WEB-INF/EcolePages/tableauBordEcole.jsp").forward(request, response);
        else if (action == ActionTypes.EcoleVersProfil || action == ActionTypes.ModifierProfil || action == ActionTypes.ModifierMdp)
            request.getRequestDispatcher("WEB-INF/EcolePages/profil_ecole.jsp").forward(request, response);
        else if (action == ActionTypes.EcoleVersMatch || action == ActionTypes.Recherche)
            request.getRequestDispatcher("WEB-INF/EcolePages/matchEcole.jsp").forward(request, response);
        else if (action == ActionTypes.EcoleVersProfilEnseignant)
            request.getRequestDispatcher("WEB-INF/EcolePages/EcoleprofilEnseignant.jsp").forward(request, response);
        else {
            request.getRequestDispatcher("WEB-INF/EcolePages/tableauBordEcole.jsp").forward(request, response);
        }
    }

}
