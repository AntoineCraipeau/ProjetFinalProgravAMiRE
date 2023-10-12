package org.amire.progav_finalproj;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.amire.progav_finalproj.repositories.UserRepository;

@Path("/users")
public class ApiEmployee {

    UserRepository userRepository = new UserRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        return userRepository.getAllUsers().toString();
    }


}
