package org.amire.progav_finalproj;

import jakarta.annotation.Resource;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.amire.progav_finalproj.model.UserSessionBean;

@Path("/users")
public class ApiEmployee {

    UserSessionBean userSessionBean = new UserSessionBean();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        return userSessionBean.getAllUsers().toString();
    }


}
