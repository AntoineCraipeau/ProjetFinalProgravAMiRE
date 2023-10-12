package org.amire.progav_finalproj;

import jakarta.annotation.Resource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.model.UserSessionBean;
import org.amire.progav_finalproj.model.EnseignantSessionBean;
import java.util.List;
import java.util.stream.Collectors;

import java.sql.Timestamp;

@Path("/users")
public class ApiEmployee {

    UserSessionBean userSessionBean = new UserSessionBean();
    EnseignantSessionBean enseignantSessionBean = new EnseignantSessionBean();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        return userSessionBean.getAllUsers().toString();
    }

    @GET
    @Path("/disponibilites")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Timestamp> getDisponibilites() {
        List<EnseignantEntity> enseignants = enseignantSessionBean.getAllEnseignants();

        List<Timestamp> dispos = enseignants.stream()
                .map(EnseignantEntity::getDisponibilites)
                .collect(Collectors.toList());

        return dispos;
    }
}
