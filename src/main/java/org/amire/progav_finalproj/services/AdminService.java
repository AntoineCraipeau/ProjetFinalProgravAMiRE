package org.amire.progav_finalproj.services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.amire.progav_finalproj.repositories.IEcoleRepository;
import org.amire.progav_finalproj.repositories.IEnseignantRepository;
import org.amire.progav_finalproj.utils.ActionTypes;
import org.amire.progav_finalproj.utils.ActionTypesUtils;

import java.io.IOException;

@Stateless
public class AdminService {

    @EJB
    private IEcoleRepository ecoleRepository;
    @EJB
    private IEnseignantRepository enseignantRepository;

    public void handleAdminRequest(HttpServletRequest request){
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

    public void handleAdminRedirection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/tableauBordAdmin.html").forward(request, response);
    }

}
