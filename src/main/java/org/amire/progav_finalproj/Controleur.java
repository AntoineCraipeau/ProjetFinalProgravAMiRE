package org.amire.progav_finalproj;

import java.io.*;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.amire.progav_finalproj.model.*;
import org.amire.progav_finalproj.utils.ActionTypes;
import org.amire.progav_finalproj.utils.ActionTypesUtils;
import org.amire.progav_finalproj.utils.UserTypes;


public class Controleur extends HttpServlet {

    @EJB
    private UserSessionBean userSessionBean;
    @EJB
    private EnseignantSessionBean enseignantSessionBean;
    @EJB
    private EcoleSessionBean ecoleSessionBean;

    UserBean unUtilisateur;
    // public static String LOGIN_VALIDE;
    // public static String MOT_DE_PASSE_VALIDE;

    public static final String MESSAGE_ERREUR_CREDENTIALS_KO = "Infos de connexion non valides. Merci de les saisir à nouveau";

    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        placerUtilisateurDansContexte(request);

        UserBean utilisateur = (UserBean) request.getAttribute("utilisateur");
        if(utilisateur.getIdUserinfo() != 0) { // Si l'utilsiateur ne vient pas d'arriver sur le site

            long idUtilisateur = utilisateur.getIdUserinfo();
            UserTypes typeUtilisateur = userSessionBean.getUserTypeFromUserId(idUtilisateur);

            switch (typeUtilisateur) {
                case ADMIN:
                    handleAdminRequest(request);
                    request.setAttribute("admin", userSessionBean.getUserById(idUtilisateur).getAdminByIdAdmin());
                    request.setAttribute("ecoles", ecoleSessionBean.getAllEcoles());
                    request.setAttribute("enseignants", enseignantSessionBean.getAllEnseignants());
                    break;
                case ECOLE:
                    handleEcoleRequest(request);
                    request.setAttribute("ecole", userSessionBean.getUserById(idUtilisateur).getEcoleByIdEcole());
                    request.setAttribute("favoris", userSessionBean.getUserById(idUtilisateur).getEcoleByIdEcole().getEcolesFavorisesByIdEcole().stream().map(EcolesFavorisEntity::getEnseignantByIdEnseignant).toArray());
                    request.setAttribute("postulations", userSessionBean.getUserById(idUtilisateur).getEcoleByIdEcole().getPostulesByIdEcole());
                    request.setAttribute("enseignants", enseignantSessionBean.getAllEnseignants());
                    break;
                case ENSEIGNANT:
                    handleEnseignantRequest(request);
                    request.setAttribute("enseignant", userSessionBean.getUserById(idUtilisateur).getEnseignantByIdEnseignant());
                    request.setAttribute("favoris", userSessionBean.getUserById(idUtilisateur).getEnseignantByIdEnseignant().getCandidatsFavorisesByIdEnseignant().stream().map(CandidatsFavorisEntity::getEcoleByIdEcole).toArray());
                    request.setAttribute("postulations", userSessionBean.getUserById(idUtilisateur).getEnseignantByIdEnseignant().getPostulesByIdEnseignant());
                    request.setAttribute("ecoles", ecoleSessionBean.getAllEcoles());
                    break;
            }
        }

        aiguillerVersLaProchainePage(request, response);
    }

    public void handleAdminRequest(HttpServletRequest request){
        // TODO : Suppression d'écoles et d'enseignants ?
    }

    public void handleEcoleRequest(HttpServletRequest request){
        // TODO : Ajout / retrait de favoris et de postulations
    }

    public void handleEnseignantRequest(HttpServletRequest request){
        // TODO : Ajout / retrait de favoris et de postulations
    }

    public boolean verifierInfosConnexion(HttpServletRequest request){
        try {

            String login = request.getParameter("champLogin");
            String password = request.getParameter("champMotDePasse");

            if (login == null || password == null) {
                return false;
            }

            UserinfoEntity user = userSessionBean.getUserByLogin(login);
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
                unUtilisateur.setIdUserinfo(userSessionBean.getUserByLogin(login).getIdUserinfo());
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
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            return;
        }

        UserTypes userType = userSessionBean.getUserTypeFromUserId(userBean.getIdUserinfo());

        switch (userType){
            case ADMIN:
                request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
                break;
            case ECOLE:
                request.getRequestDispatcher("WEB-INF/ecole.jsp").forward(request, response);
                break;
            case ENSEIGNANT:
                request.getRequestDispatcher("WEB-INF/enseignant.jsp").forward(request, response);
                break;
        }
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