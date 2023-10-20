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
import org.amire.progav_finalproj.utils.ActionTypes;
import org.amire.progav_finalproj.utils.ActionTypesUtils;
import org.amire.progav_finalproj.utils.UserTypes;

import java.io.IOException;

public class ControleurInscriptions extends HttpServlet implements Controleurs {

    @EJB
    private UserRepository userRepository;
    @EJB
    private EnseignantRepository enseignantRepository;
    @EJB
    private EcoleRepository ecoleRepository;

    UserBean unUtilisateur;

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

    @Transactional
    public void createEcoleAccount(HttpServletRequest request){
        //Creation d'une EcoleEntity et d'une UserInfoEntity
        request.setAttribute("messageErreur", "");

        EcoleEntity ecole = EcoleFactory.buildEmptyEcole();
        ecoleRepository.addEcole(ecole);

        UserinfoEntity user = new UserinfoEntity();
        user.setLogin(request.getParameter("champLogin"));
        user.setPassword(request.getParameter("champMotDePasse"));
        user.setIdEcole(ecole.getIdEcole());
        userRepository.addUser(user);

        //Ajout de l'utilisateur dans la session
        unUtilisateur.setIdUserinfo(userRepository.getUserByLogin(user.getLogin()).getIdUserinfo());
        request.getSession().setAttribute("utilisateur", unUtilisateur);
    }

    @Transactional
    public void createEnseignantAccount(HttpServletRequest request){
        //Creation d'une EnseignantEntity et d'une UserInfoEntity
        request.setAttribute("messageErreur", "");

        EnseignantEntity enseignant = EnseignantFactory.buildEmptyEnseignant();
        enseignantRepository.addEnseignant(enseignant);

        UserinfoEntity user = new UserinfoEntity();
        user.setLogin(request.getParameter("champLogin"));
        user.setPassword(request.getParameter("champMotDePasse"));
        user.setIdEnseignant(enseignant.getIdEnseignant());
        userRepository.addUser(user);

        //Ajout de l'utilisateur dans la session
        unUtilisateur.setIdUserinfo(userRepository.getUserByLogin(user.getLogin()).getIdUserinfo());
        request.getSession().setAttribute("utilisateur", unUtilisateur);
    }

    public void fillEcoleAccount(HttpServletRequest request){
        //Remplissage d'une EcoleEntity
        UserinfoEntity user = userRepository.getUserById(unUtilisateur.getIdUserinfo());
        EcoleEntity ecole = user.getEcoleByIdEcole();
        EcoleEntity fromRequest = EcoleFactory.buildEcoleFromRequest(request);
        fromRequest.setIdEcole(ecole.getIdEcole());
        ecoleRepository.editEcole(fromRequest);

        //Invalidation de la session pour être redirigé vers la page de login
        request.getSession().invalidate();
        unUtilisateur = null;
    }

    public void fillEnseignantAccount(HttpServletRequest request){
        //Remplissage d'une EnseignantEntity
        UserinfoEntity user = userRepository.getUserById(unUtilisateur.getIdUserinfo());
        EnseignantEntity enseignant = user.getEnseignantByIdEnseignant();
        EnseignantEntity fromRequest = EnseignantFactory.buildEnseignantFromRequest(request);
        fromRequest.setIdEnseignant(enseignant.getIdEnseignant());
        enseignantRepository.editEnseignant(fromRequest);

        //Invalidation de la session pour être redirigé vers la page de login
        request.getSession().invalidate();
        unUtilisateur = null;
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
            request.getRequestDispatcher("/WEB-INF/pages_login.jsp").forward(request, response);
            return;
        }

        if(action != ActionTypes.StartRegister){
            //Cas erreur
            request.getRequestDispatcher("/WEB-INF/pages_login.jsp").forward(request, response);
            return;
        }

        UserTypes userType = userRepository.getUserTypeFromUserId(unUtilisateur.getIdUserinfo());

        switch (userType){
            case ECOLE:
                request.getRequestDispatcher("/WEB-INF/pages_form_ecole.jsp").forward(request, response);
                break;
            case ENSEIGNANT:
                request.getRequestDispatcher("/WEB-INF/pages_form_enseignant.jsp").forward(request, response);
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
