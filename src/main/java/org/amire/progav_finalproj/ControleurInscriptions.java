package org.amire.progav_finalproj;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.amire.progav_finalproj.factories.EcoleFactory;
import org.amire.progav_finalproj.factories.EnseignantFactory;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.model.UserinfoEntity;
import org.amire.progav_finalproj.repositories.*;
import org.amire.progav_finalproj.services.IAuthService;
import org.amire.progav_finalproj.utils.ActionTypes;
import org.amire.progav_finalproj.utils.ActionTypesUtils;
import org.amire.progav_finalproj.utils.UserTypes;

import java.io.IOException;

public class ControleurInscriptions extends HttpServlet implements Controleurs {

    @EJB
    private IUserRepository userRepository;
    @EJB
    private IEnseignantRepository enseignantRepository;
    @EJB
    private IEcoleRepository ecoleRepository;
    @EJB
    private IAuthService authService;

    UserBean unUtilisateur;

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        placerUtilisateurDansContexte(request);

        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);
        UserTypes userType;
        String chosenType;

        if(unUtilisateur != null && unUtilisateur.getIdUserinfo() != 0 && action == ActionTypes.EndRegister){
            //L'utilisateur a déjà renseigné un login et un mot de passe
            userType = userRepository.getUserTypeFromUserId(unUtilisateur.getIdUserinfo());
            switch (userType){
                case ECOLE:
                    fillEcoleAccount(request);
                    break;
                case ENSEIGNANT:
                    fillEnseignantAccount(request);
                    break;
                default:
                    break;
            }
        } else if(action == ActionTypes.StartRegister) {
            chosenType = request.getParameter("userType");
            switch (chosenType) {
                case "ecole":
                    createEcoleAccount(request);
                    break;
                case "enseignant":
                    createEnseignantAccount(request);
                    break;
                default:
                    break;
            }
        }

        aiguillerVersLaProchainePage(request, response);
    }

    public void createEcoleAccount(HttpServletRequest request){
        authService.createEcoleAccount(request, unUtilisateur);
    }

    public void createEnseignantAccount(HttpServletRequest request){
        authService.createEnseignantAccount(request, unUtilisateur);
    }

    public void fillEcoleAccount(HttpServletRequest request){
        //Remplissage d'une EcoleEntity
        UserinfoEntity user = userRepository.getUserById(unUtilisateur.getIdUserinfo());
        EcoleEntity ecole = user.getEcole();
        EcoleEntity fromRequest = EcoleFactory.buildEcoleFromRequest(request);
        fromRequest.setIdEcole(ecole.getIdEcole());
        ecoleRepository.editEcole(fromRequest);

        //Invalidation de la session pour être redirigé vers la page de login
        request.getSession().invalidate();
        unUtilisateur = null;
        request.setAttribute("messageSucces", MESSAGE_SUCCES_CREATION_COMPTE);
    }

    public void fillEnseignantAccount(HttpServletRequest request){
        //Remplissage d'une EnseignantEntity
        UserinfoEntity user = userRepository.getUserById(unUtilisateur.getIdUserinfo());
        EnseignantEntity enseignant = user.getEnseignant();
        EnseignantEntity fromRequest = EnseignantFactory.buildEnseignantFromRequest(request);
        fromRequest.setIdEnseignant(enseignant.getIdEnseignant());
        enseignantRepository.editEnseignant(fromRequest);

        //Invalidation de la session pour être redirigé vers la page de login
        request.getSession().invalidate();
        unUtilisateur = null;
        request.setAttribute("messageSucces", MESSAGE_SUCCES_CREATION_COMPTE);
    }

    @Override
    public void placerUtilisateurDansContexte(HttpServletRequest request) {
        unUtilisateur = new UserBean();

        UserBean sessionUtilisateur = (UserBean) request.getSession().getAttribute("utilisateur");
        if(sessionUtilisateur != null){
            unUtilisateur.setIdUserinfo(sessionUtilisateur.getIdUserinfo());
        }
    }

    @Override
    public void aiguillerVersLaProchainePage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);
        if(unUtilisateur == null || unUtilisateur.getIdUserinfo() == 0){
            //Soit l'utilisateur n'a pas encore renseigné de login et de mot de passe,
            // soit il a fini de renseigner les détails de son compte
            request.getRequestDispatcher("/WEB-INF/pages-login.jsp").forward(request, response);
            return;
        }

        if(action != ActionTypes.StartRegister){
            //Cas erreur
            request.getRequestDispatcher("/WEB-INF/pages-login.jsp").forward(request, response);
            return;
        }

        UserTypes userType = userRepository.getUserTypeFromUserId(unUtilisateur.getIdUserinfo());

        switch (userType){
            case ECOLE:
                request.getRequestDispatcher("/WEB-INF/EcolePages/pages_form_ecole.jsp").forward(request, response);
                break;
            case ENSEIGNANT:
                request.getRequestDispatcher("/WEB-INF/EnseignantPages/pages_form_enseignant.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/pages_login.jsp").forward(request, response);
                break;
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }
}
