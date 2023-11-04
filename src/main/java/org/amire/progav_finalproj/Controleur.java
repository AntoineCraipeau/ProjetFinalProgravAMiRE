package org.amire.progav_finalproj;

import java.io.*;
import java.util.Arrays;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.amire.progav_finalproj.dto.*;
import org.amire.progav_finalproj.factories.EcoleFactory;
import org.amire.progav_finalproj.factories.EnseignantFactory;
import org.amire.progav_finalproj.factories.PostuleFactory;
import org.amire.progav_finalproj.model.*;
import org.amire.progav_finalproj.repositories.*;
import org.amire.progav_finalproj.services.PreferenceMatcherService;
import org.amire.progav_finalproj.services.SearchService;
import org.amire.progav_finalproj.utils.ActionTypes;
import org.amire.progav_finalproj.utils.ActionTypesUtils;
import org.amire.progav_finalproj.utils.UserTypes;


public class Controleur extends HttpServlet implements Controleurs {

    @EJB
    private UserRepository userRepository;
    @EJB
    private EnseignantRepository enseignantRepository;
    @EJB
    private FavorisEnseignantRepository favorisEnseignantRepository;
    @EJB
    private EcoleRepository ecoleRepository;
    @EJB
    private FavorisEcoleRepository favorisEcoleRepository;
    @EJB
    private PostuleRepository postuleRepository;
    @EJB
    private PreferenceMatcherService preferenceMatcherService;
    @EJB
    private SearchService searchService;

    UserBean unUtilisateur;


    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        placerUtilisateurDansContexte(request);

        try {

            if (unUtilisateur != null && unUtilisateur.getIdUserinfo() != 0) { // Si l'utilsiateur ne vient pas d'arriver sur le site

                long idUtilisateur = unUtilisateur.getIdUserinfo();
                UserTypes typeUtilisateur = userRepository.getUserTypeFromUserId(idUtilisateur);
                String searchQuery = request.getParameter("query");

                switch (typeUtilisateur) {
                    case ADMIN:
                        handleAdminRequest(request);
                        request.setAttribute("userInfo", userRepository.getUserById(idUtilisateur));
                        request.setAttribute("admin", userRepository.getUserById(idUtilisateur).getAdmin());
                        request.setAttribute("ecoles", ecoleRepository.getAllEcoles());
                        request.setAttribute("enseignants", enseignantRepository.getAllEnseignants());
                        break;
                    case ECOLE:
                        handleEcoleRequest(request);
                        long idEcole = userRepository.getUserById(idUtilisateur).getEcole().getIdEcole();
                        request.setAttribute("userInfo", userRepository.getUserById(idUtilisateur));
                        request.setAttribute("ecole", new EcoleProfileInfoDto(ecoleRepository.getEcoleById(idEcole)));
                        request.setAttribute("favoris", favorisEcoleRepository.getAllFavorisOfEcoleById(idEcole));
                        request.setAttribute("postulations", postuleRepository.getAllPostulesByEcoleId(idEcole).stream().map(PostuleListElementDto::new).toArray());
                        request.setAttribute("enseignants", searchService.filterAsEcole(preferenceMatcherService.getMatchingEnseignant(idEcole), searchQuery));
                        break;
                    case ENSEIGNANT:
                        handleEnseignantRequest(request);
                        long idEnseignant = userRepository.getUserById(idUtilisateur).getEnseignant().getIdEnseignant();
                        request.setAttribute("userInfo", userRepository.getUserById(idUtilisateur));
                        request.setAttribute("enseignant", new EnseignantProfileInfoDto(enseignantRepository.getEnseignantById(idEnseignant)));
                        request.setAttribute("favoris", favorisEnseignantRepository.getAllFavorisOfCandidatById(idEnseignant));
                        request.setAttribute("postulations", postuleRepository.getAllPostulesByEnseignantId(idEnseignant).stream().map(PostuleListElementDto::new).toArray());
                        request.setAttribute("ecoles", searchService.filterAsEnseignant(preferenceMatcherService.getMatchingEcole(idEnseignant), searchQuery));
                        break;

                }

            }

        } catch (Exception e) { // Des exceptions peuvent être levées par les repositories
            request.setAttribute("messageErreur", e.getMessage());
            System.out.println("Main loop error:"+ e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
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
                    request.setAttribute("messageSucces", MESSAGE_SUCCES_MODIFICATION_COMPTE);
                } catch (Exception e) {
                    request.setAttribute("messageErreur", MESSAGE_ERREUR_PROFIL_MODIFICATION_ECHEC);
                }
            }
    }

    public void handleEnseignantRequest(HttpServletRequest request){
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
                    request.setAttribute("messageSucces", MESSAGE_SUCCES_MODIFICATION_COMPTE);
                } catch (Exception e) {
                    request.setAttribute("messageErreur", MESSAGE_ERREUR_PROFIL_MODIFICATION_ECHEC);
                }
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
        ActionTypes[] noContextActions = {ActionTypes.ToLogin, ActionTypes.ToRegister, ActionTypes.Login, ActionTypes.Logout};

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

        } else if (!Arrays.asList(noContextActions).contains(action)){
            UserBean utilisateurInfoSession = (UserBean) request.getSession().getAttribute("utilisateur");
            if (utilisateurInfoSession != null){
                unUtilisateur.setIdUserinfo(utilisateurInfoSession.getIdUserinfo());
                System.out.println("Le contexte utilisateur a été trouvé avec l'action " + action);
            } else {
                System.out.println("Le contexte utilisateur n'a pas été trouvé avec l'action " + action);
            }
        }

        //Si l'utilisateur vient d'arriver, l'IdUserinfo sera à 0

        request.setAttribute("utilisateur", unUtilisateur);
    }

    public void aiguillerVersLaProchainePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);
        UserBean userBean = (UserBean) request.getAttribute("utilisateur");

        if(userBean.getIdUserinfo() == 0){ // Si l'utilisateur vient d'arriver sur le site ou de se déconnecter
            if(action == ActionTypes.ToRegister)
                request.getRequestDispatcher("WEB-INF/pages-register.jsp").forward(request, response);
            else
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
            request.getRequestDispatcher("WEB-INF/EcolePages/tableauBordEcole.jsp").forward(request, response);
        else if (action == ActionTypes.EcoleVersProfil || action == ActionTypes.ModifierProfil)
            request.getRequestDispatcher("WEB-INF/EcolePages/profil_ecole.jsp").forward(request, response);
        else if (action == ActionTypes.EcoleVersMatch || action == ActionTypes.Recherche)
            request.getRequestDispatcher("WEB-INF/EcolePages/matchEcole.jsp").forward(request, response);
        else {
            request.getRequestDispatcher("WEB-INF/EcolePages/tableauBordEcole.jsp").forward(request, response);
    }}

    public void handleEnseignantRedirection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        if(action == ActionTypes.EnseignantVersDashboard)
            request.getRequestDispatcher("WEB-INF/EnseignantPages/tableauBordEnseignant.jsp").forward(request, response);
        else if (action == ActionTypes.EnseignantVersProfil || action == ActionTypes.ModifierProfil)
            request.getRequestDispatcher("WEB-INF/EnseignantPages/profil_enseignant.jsp").forward(request, response);
        else if (action == ActionTypes.EnseignantVersMatch || action == ActionTypes.Recherche)
            request.getRequestDispatcher("WEB-INF/EnseignantPages/matchEnseignant.jsp").forward(request, response);
        else {request.getRequestDispatcher("WEB-INF/EnseignantPages/tableauBordEnseignant.jsp").forward(request, response);}
    }

    public void init() {
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