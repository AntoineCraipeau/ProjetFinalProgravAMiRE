package org.amire.progav_finalproj.services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.amire.progav_finalproj.Controleurs;
import org.amire.progav_finalproj.UserBean;
import org.amire.progav_finalproj.factories.EcoleFactory;
import org.amire.progav_finalproj.factories.EnseignantFactory;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.model.UserinfoEntity;
import org.amire.progav_finalproj.repositories.IEcoleRepository;
import org.amire.progav_finalproj.repositories.IEnseignantRepository;
import org.amire.progav_finalproj.repositories.IUserRepository;

@Stateless
public class AuthService implements IAuthService {

    @EJB
    private IUserRepository userRepository;
    @EJB
    private IEcoleRepository ecoleRepository;
    @EJB
    private IEnseignantRepository enseignantRepository;

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

    public void connexion(HttpServletRequest request, UserBean unUtilisateur){
        if(!verifierInfosConnexion(request)){
            request.setAttribute("messageErreur", Controleurs.MESSAGE_ERREUR_CREDENTIALS_KO);
        } else{
            String login = request.getParameter("champLogin");
            unUtilisateur.setIdUserinfo(userRepository.getUserByLogin(login).getIdUserinfo());
            request.getSession().setAttribute("utilisateur", unUtilisateur);
        }
    }

    public void decoderSession(HttpServletRequest request, UserBean unUtilisateur){
        if(request.getSession().getAttribute("utilisateur") != null){
            UserBean tempUtilisateur = (UserBean) request.getSession().getAttribute("utilisateur");
            unUtilisateur.setIdUserinfo(tempUtilisateur.getIdUserinfo());
            System.out.println("Le contexte utilisateur a été trouvé");
        } else {
            System.out.println("Le contexte utilisateur n'a pas été trouvé");
        }
    }

    public void deconnexion(HttpServletRequest request, UserBean unUtilisateur){
        request.getSession().removeAttribute("utilisateur");
        request.getSession().invalidate();
        unUtilisateur.setIdUserinfo(0);
    }

    public void modifierMotDePasse (HttpServletRequest request, UserBean unUtilisateur){
        try {
            String mdp = request.getParameter("currentPassword");
            String newMdp = request.getParameter("newPassword");
            String newMdpConfirm = request.getParameter("renewPassword");
            UserinfoEntity user = userRepository.getUserById(unUtilisateur.getIdUserinfo());
            if(!user.getPassword().equals(mdp)) {
                request.setAttribute("messageErreur", Controleurs.MESSAGE_ERREUR_PROFIL_MODIFICATION_MDP_WRONG_OLD);
                return;
            }
            if(!newMdp.equals(newMdpConfirm)){
                request.setAttribute("messageErreur", Controleurs.MESSAGE_ERREUR_PROFIL_MODIFICATION_MDP_DIFFERENT);
                return;
            }
            user.setPassword(newMdp);
            userRepository.editUser(user);
            request.setAttribute("messageSucces", Controleurs.MESSAGE_SUCCES_MODIFICATION_COMPTE);
        } catch (Exception e) {
            request.setAttribute("messageErreur", Controleurs.MESSAGE_ERREUR_PROFIL_MODIFICATION_ECHEC);
        }
    }

    @Transactional
    public void createEcoleAccount(HttpServletRequest request, UserBean unUtilisateur){

        if(userRepository.getUserByLogin(request.getParameter("champLogin")) != null){
            request.setAttribute("messageErreur", Controleurs.MESSAGE_ERREUR_CREDENTIALS_USED);
            return;
        }

        //Creation d'une EcoleEntity et d'une UserInfoEntity
        request.setAttribute("messageErreur", "");

        EcoleEntity ecole = EcoleFactory.buildEmptyEcole();
        ecoleRepository.addEcole(ecole);

        UserinfoEntity user = new UserinfoEntity();
        user.setLogin(request.getParameter("champLogin"));
        user.setPassword(request.getParameter("champMotDePasse"));
        user.setIdEcole(ecole.getIdEcole());
        user.setEcole(ecole);
        userRepository.addUser(user);

        //Ajout de l'utilisateur dans la session
        unUtilisateur.setIdUserinfo(userRepository.getUserByLogin(user.getLogin()).getIdUserinfo());
        request.getSession().setAttribute("utilisateur", unUtilisateur);
    }

    @Transactional
    public void createEnseignantAccount(HttpServletRequest request, UserBean unUtilisateur){

        if(userRepository.getUserByLogin(request.getParameter("champLogin")) != null){
            request.setAttribute("messageErreur", Controleurs.MESSAGE_ERREUR_CREDENTIALS_USED);
            return;
        }

        //Creation d'une EnseignantEntity et d'une UserInfoEntity
        request.setAttribute("messageErreur", "");

        EnseignantEntity enseignant = EnseignantFactory.buildEmptyEnseignant();
        enseignantRepository.addEnseignant(enseignant);

        UserinfoEntity user = new UserinfoEntity();
        user.setLogin(request.getParameter("champLogin"));
        user.setPassword(request.getParameter("champMotDePasse"));
        user.setIdEnseignant(enseignant.getIdEnseignant());
        user.setEnseignant(enseignant);
        userRepository.addUser(user);

        //Ajout de l'utilisateur dans la session
        unUtilisateur.setIdUserinfo(userRepository.getUserByLogin(user.getLogin()).getIdUserinfo());
        request.getSession().setAttribute("utilisateur", unUtilisateur);
    }
}
