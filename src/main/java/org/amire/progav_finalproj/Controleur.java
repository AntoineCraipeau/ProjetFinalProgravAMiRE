package org.amire.progav_finalproj;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.amire.progav_finalproj.dto.*;
import org.amire.progav_finalproj.repositories.*;
import org.amire.progav_finalproj.services.*;
import org.amire.progav_finalproj.utils.*;


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
    @EJB
    private AuthService authService;
    @EJB
    private AdminService adminService;
    @EJB
    private EcoleService ecoleService;
    @EJB
    private EnseignantService enseignantService;
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
                        adminService.handleAdminRequest(request);
                        request.setAttribute("userInfo", userRepository.getUserById(idUtilisateur));
                        request.setAttribute("admin", userRepository.getUserById(idUtilisateur).getAdmin());
                        request.setAttribute("ecoles", ecoleRepository.getAllEcoles());
                        request.setAttribute("enseignants", enseignantRepository.getAllEnseignants());
                        break;
                    case ECOLE:
                        ecoleService.handleEcoleRequest(request,unUtilisateur);
                        long idEcole = userRepository.getUserById(idUtilisateur).getEcole().getIdEcole();
                        request.setAttribute("userInfo", userRepository.getUserById(idUtilisateur));
                        request.setAttribute("ecole", new EcoleProfileInfoDto(ecoleRepository.getEcoleById(idEcole)));
                        request.setAttribute("favoris", favorisEcoleRepository.getAllFavorisOfEcoleById(idEcole));
                        request.setAttribute("postulations", postuleRepository.getAllPostulesByEcoleId(idEcole).stream().map(PostuleListElementDto::new).toArray());
                        request.setAttribute("enseignants", searchService.filterAsEcole(preferenceMatcherService.getMatchingEnseignant(idEcole), searchQuery));
                        break;
                    case ENSEIGNANT:
                        enseignantService.handleEnseignantRequest(request,unUtilisateur);
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
    public void placerUtilisateurDansContexte(HttpServletRequest request){

        unUtilisateur = new UserBean();
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);
        ArrayList<ActionTypes> noContextActions = new ArrayList<>(Arrays.asList(ActionTypes.ToLogin, ActionTypes.ToRegister, ActionTypes.Login, ActionTypes.Logout));

        if(action == ActionTypes.Logout){
            authService.deconnexion(request);
        }

        if(action == ActionTypes.Login){
            authService.connexion(request, unUtilisateur);
        } else if (!noContextActions.contains(action)){ // Si l'action n'est pas une action sans contexte utilisateur
            authService.decoderSession(request, unUtilisateur); // Placer l'utilisateur dans le contexte
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
                adminService.handleAdminRedirection(request, response);
                break;
            case ECOLE:
                ecoleService.handleEcoleRedirection(request, response);
                break;
            case ENSEIGNANT:
                enseignantService.handleEnseignantRedirection(request, response);
                break;
        }
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