package org.amire.progav_finalproj;


import java.io.*;
import java.util.List;


import jakarta.ejb.EJB;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.amire.progav_finalproj.model.EmployeeSessionBean;
import org.amire.progav_finalproj.model.EmployesEntity;
import org.amire.progav_finalproj.model.EmployeeFactory;
import org.amire.progav_finalproj.utils.ActionTypes;
import org.amire.progav_finalproj.utils.ActionTypesUtils;

//@WebServlet(name = "Controleur", value = "/")
public class Controleur extends HttpServlet {

    @EJB
    private EmployeeSessionBean employesSessionBean;
    UserBean unUtilisateur;
    public static String LOGIN_VALIDE;
    public static String MOT_DE_PASSE_VALIDE;

    public static final String MESSAGE_ERREUR_CREDENTIALS_KO = "Infos de connexion non valides. Merci de les saisir à nouveau";

    public void processRequest (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // Trouver la value de l'input submit ayant mené à cette servlet

        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        System.out.println("action = " + action);

        // Les données saisies par l'utilisateur sont placées dans le contexte
        placerUtilisateurDansContexte(request);

        // Traitement de l'action
        switch (action){
            case Details:
                int id_employee_detail = Integer.parseInt(request.getParameter("idEmploye"));
                EmployesEntity employeDetail = employesSessionBean.getEmployeeById(id_employee_detail);
                request.setAttribute("anemploye", employeDetail);
                break;

            case Edit:
                EmployesEntity employeAEditer = EmployeeFactory.getEmployeeInfoFromRequest(request);
                employesSessionBean.editEmployee(employeAEditer);
                break;

            case Supprimer:
                int id_supprimer = Integer.parseInt(request.getParameter("idEmploye"));
                EmployesEntity employeASupprimer = employesSessionBean.getEmployeeById(id_supprimer);
                employesSessionBean.deleteEmployee(employeASupprimer);
                break;
        }


        // Seuls les actions "Entry" et "Details" ne nécessitent pas de récupérer la liste des employés
        if(action != ActionTypes.Entry && action != ActionTypes.Details){

            // Récupération de la liste de tous les employés via notre service getTousLesEmployes()
            List<EmployesEntity> tousLesEmployees = employesSessionBean.getAllEmployees();

            // Je mets la liste dans l'objet request afin qu'il soit accessible dans les autres couches, notamment la Vue
            request.setAttribute("employees", tousLesEmployees);

        }

        aiguillerVersLaProchainePage(request, response);
    }

    // Une tâche <-> une méthode
    public boolean verifierInfosConnexion(UserBean unUtilisateur){
        return (unUtilisateur.getLogin().equals(LOGIN_VALIDE) && unUtilisateur.getPassword().equals(MOT_DE_PASSE_VALIDE));
    }


    public void placerUtilisateurDansContexte(HttpServletRequest request){

        unUtilisateur = new UserBean();
        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        if(action == ActionTypes.Login){
            unUtilisateur.setLogin(request.getParameter("champLogin"));
            unUtilisateur.setPassword(request.getParameter("champMotDePasse"));
            request.getSession().setAttribute("utilisateur", unUtilisateur);
        } else {
            UserBean utilisateurInfoSession = (UserBean) request.getSession().getAttribute("utilisateur");
            if (utilisateurInfoSession != null){
                unUtilisateur.setLogin(utilisateurInfoSession.getLogin());
                unUtilisateur.setPassword(utilisateurInfoSession.getPassword());
            }

        }


        request.setAttribute("utilisateur", unUtilisateur);
    }

    public void aiguillerVersLaProchainePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ActionTypes action = ActionTypesUtils.getActionTypesFromRequest(request);

        //Si l'utilisateur arrive sur le site, ou si les infos de connexions sont nulles.
        if(action == ActionTypes.Entry || unUtilisateur == null || unUtilisateur.getLogin() == null || unUtilisateur.getPassword() == null)

            request.getRequestDispatcher("WEB-INF/index_d.jsp").forward(request, response);


        //Sinon on vérifie les infos de connexion
        if (verifierInfosConnexion(unUtilisateur)){

            if(action == ActionTypes.Details)
                request.getRequestDispatcher("WEB-INF/detail.jsp").forward(request, response);
            else
                request.getRequestDispatcher("WEB-INF/bienvenue.jsp").forward(request, response);

        }else{

            request.setAttribute("messageErreur", MESSAGE_ERREUR_CREDENTIALS_KO);
            request.getRequestDispatcher("WEB-INF/index_d.jsp").forward(request, response);

        }
    }

    public void init() {
        ServletContext context = getServletContext();
        LOGIN_VALIDE = context.getInitParameter("login");
        MOT_DE_PASSE_VALIDE = context.getInitParameter("password");
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