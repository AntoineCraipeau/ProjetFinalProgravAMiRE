package org.amire.progav_finalproj;

import java.io.*;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.amire.progav_finalproj.model.*;
import org.amire.progav_finalproj.repositories.*;
import org.amire.progav_finalproj.utils.ActionTypes;
import org.amire.progav_finalproj.utils.ActionTypesUtils;
import org.amire.progav_finalproj.utils.UserTypes;


public class Controleur extends HttpServlet {

    @EJB
    private UserRepository userRepository;
    @EJB
    private EnseignantRepository enseignantRepository;
    @EJB
    private CandidatsFavorisRepository candidatsFavorisRepository;
    @EJB
    private EcoleRepository ecoleRepository;
    @EJB
    private EcoleFavorisRepository ecoleFavorisRepository;

    UserBean unUtilisateur;
    // public static String LOGIN_VALIDE;
    // public static String MOT_DE_PASSE_VALIDE;

    public static final String MESSAGE_ERREUR_CREDENTIALS_KO = "Infos de connexion non valides. Merci de les saisir à nouveau";

    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        placerUtilisateurDansContexte(request);

        if(unUtilisateur != null && unUtilisateur.getIdUserinfo() != 0) { // Si l'utilsiateur ne vient pas d'arriver sur le site

            long idUtilisateur = unUtilisateur.getIdUserinfo();
            UserTypes typeUtilisateur = userRepository.getUserTypeFromUserId(idUtilisateur);

            switch (typeUtilisateur) {
                case ADMIN:
                    handleAdminRequest(request);
                    request.setAttribute("userInfo", userRepository.getUserById(idUtilisateur));
                    request.setAttribute("admin", userRepository.getUserById(idUtilisateur).getAdminByIdAdmin());
                    request.setAttribute("ecoles", ecoleRepository.getAllEcoles());
                    request.setAttribute("enseignants", enseignantRepository.getAllEnseignants());
                    break;
                case ECOLE:
                    handleEcoleRequest(request);
                    request.setAttribute("userInfo", userRepository.getUserById(idUtilisateur));
                    request.setAttribute("ecole", userRepository.getUserById(idUtilisateur).getEcoleByIdEcole());
                    request.setAttribute("favoris", userRepository.getUserById(idUtilisateur).getEcoleByIdEcole().getEcolesFavorisesByIdEcole().stream().map(EcolesFavorisEntity::getEnseignantByIdEnseignant).toArray());
                    request.setAttribute("postulations", userRepository.getUserById(idUtilisateur).getEcoleByIdEcole().getPostulesByIdEcole());
                    request.setAttribute("enseignants", enseignantRepository.getAllEnseignants());
                    break;
                case ENSEIGNANT:
                    handleEnseignantRequest(request);
                    request.setAttribute("userInfo", userRepository.getUserById(idUtilisateur));
                    request.setAttribute("enseignant", userRepository.getUserById(idUtilisateur).getEnseignantByIdEnseignant());
                    request.setAttribute("favoris", userRepository.getUserById(idUtilisateur).getEnseignantByIdEnseignant().getCandidatsFavorisesByIdEnseignant().stream().map(CandidatsFavorisEntity::getEcoleByIdEcole).toArray());
                    request.setAttribute("postulations", userRepository.getUserById(idUtilisateur).getEnseignantByIdEnseignant().getPostulesByIdEnseignant());
                    request.setAttribute("ecoles", ecoleRepository.getAllEcoles());
                    break;
            }
        }

        aiguillerVersLaProchainePage(request, response);
    }

    public void handleAdminRequest(HttpServletRequest request){
        // TODO : Suppression d'écoles et d'enseignants ?
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        switch (action) {
            case SupprimerEcole:
                long idEcole = Long.parseLong(request.getParameter("idEcole"));
                ecoleRepository.deleteEcoleById(idEcole);
                break;
            case SupprimerEnseignant:
                long idEnseignant = Long.parseLong(request.getParameter("idEnseignant"));
                enseignantRepository.deleteEnseignantById(idEnseignant);
                break;
        }

    }

    public void handleEcoleRequest(HttpServletRequest request){
        // TODO : Ajout / retrait de favoris et de postulations
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        EcoleEntity ecole = userRepository.getUserById(unUtilisateur.getIdUserinfo()).getEcoleByIdEcole();
        long idEcole = ecole.getIdEcole();
        long idEnseignant = request.getParameter("idEnseignant") != null ? Long.parseLong(request.getParameter("idEnseignant")) : 0;

        switch (action) {
            case AjoutFavorisEcole:
                ecoleFavorisRepository.addEcoleFavoris(idEcole, idEnseignant);
                break;
            case RetraitFavorisEcole:
                ecoleFavorisRepository.removeEcoleFavoris(idEcole, idEnseignant);
                break;
            case AjoutPosulationEcole:
                //userSessionBean.ajouterEcolePostulation(idEcole, idEnseignant);
                break;
            case RetraitPosulationEcole:
                //userSessionBean.retirerEcolePostulation(idEcole, idEnseignant);
                break;
        }
    }

    public void handleEnseignantRequest(HttpServletRequest request){
        // TODO : Ajout / retrait de favoris et de postulations
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        EnseignantEntity enseignant = userRepository.getUserById(unUtilisateur.getIdUserinfo()).getEnseignantByIdEnseignant();
        long idEnseignant = enseignant.getIdEnseignant();
        long idEcole = request.getParameter("idEcole") != null ? Long.parseLong(request.getParameter("idEcole")) : 0;

        switch (action){
            case AjoutFavorisEnseignant:
                candidatsFavorisRepository.addCandidatsFavoris(idEnseignant, idEcole);
                break;
            case RetraitFavorisEnseignant:
                candidatsFavorisRepository.removeCandidatsFavoris(idEnseignant, idEcole);
                break;
            case AjoutPosulationEnseignant:
                //userSessionBean.ajouterEnseignantPostulation(idEcole, idEnseignant);
                break;
            case RetraitPosulationEnseignant:
                //userSessionBean.retirerEnseignantPostulation(idEcole, idEnseignant);
                break;
        }
    }

    public boolean verifierInfosConnexion(HttpServletRequest request){
        try {

            String login = request.getParameter("champLogin");
            String password = request.getParameter("champMotDePasse");

            if (login == null || password == null) {
                return false;
            }

            UserinfoEntity user = userRepository.getUserByLogin(login);
            return password.equals(user.getPassword());

        } catch (Exception e) { // Une exception est levée par getUserByLogin si le login n'existe pas
            return false;
        }
    }


    public void placerUtilisateurDansContexte(HttpServletRequest request){

        unUtilisateur = new UserBean();
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        if(action == ActionTypes.Logout){
            request.getSession().setAttribute("utilisateur", null);
            request.getSession().invalidate();
        }

        if(action == ActionTypes.Login){
            if(!verifierInfosConnexion(request)){
                request.setAttribute("messageErreur", MESSAGE_ERREUR_CREDENTIALS_KO);
            } else{
                request.setAttribute("messageErreur", "");
                String login = request.getParameter("champLogin");
                unUtilisateur.setIdUserinfo(userRepository.getUserByLogin(login).getIdUserinfo());
                request.getSession().setAttribute("utilisateur", unUtilisateur);
            }

        } else if (action != ActionTypes.Entry && action != ActionTypes.Logout){
            UserBean utilisateurInfoSession = (UserBean) request.getSession().getAttribute("utilisateur");
            if (utilisateurInfoSession != null){
                unUtilisateur.setIdUserinfo(utilisateurInfoSession.getIdUserinfo());
            }
        }

        //Si l'utilisateur vient d'arriver, l'IdUserinfo sera à 0

        request.setAttribute("utilisateur", unUtilisateur);
    }

    public void aiguillerVersLaProchainePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserBean userBean = (UserBean) request.getAttribute("utilisateur");

        if(userBean.getIdUserinfo() == 0){ // Si l'utilisateur vient d'arriver sur le site ou de se déconnecter
            request.getRequestDispatcher("WEB-INF/pages-login.jsp").forward(request, response);
            return;
        }

        UserTypes userType = userRepository.getUserTypeFromUserId(userBean.getIdUserinfo());

        switch (userType){
            case ADMIN:
                request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
                break;
            case ECOLE:
                handleEcoleRedirection(request, response);
                break;
            case ENSEIGNANT:
                handleEnseignantRedirection(request, response);
                break;
        }
    }

    public void handleEcoleRedirection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        if(action == ActionTypes.EcoleVersDashboard)
            request.getRequestDispatcher("WEB-INF/tableauBordEcole.jsp").forward(request, response);
        else
            request.getRequestDispatcher("WEB-INF/profil_ecole.jsp").forward(request, response);

    }

    public void handleEnseignantRedirection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        if(action != ActionTypes.EnseignantVersDashboard)
            request.getRequestDispatcher("WEB-INF/tableauBordEnseignant.jsp").forward(request, response);
        else
            request.getRequestDispatcher("WEB-INF/profil_enseignant.jsp").forward(request, response);
    }

    public void init() {
        // ServletContext context = getServletContext();
        // LOGIN_VALIDE = context.getInitParameter("login");
        // MOT_DE_PASSE_VALIDE = context.getInitParameter("password");
    }

    public void destroy() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

}