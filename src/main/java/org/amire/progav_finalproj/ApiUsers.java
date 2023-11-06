package org.amire.progav_finalproj;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import org.amire.progav_finalproj.dto.EnseignantProfileInfoDto;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.dto.EcoleProfileInfoDto;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.repositories.EnseignantRepository;
import org.amire.progav_finalproj.repositories.EcoleRepository;
import org.amire.progav_finalproj.repositories.UserRepository;

import java.util.*;


@Path("/users")
public class ApiUsers {

    EnseignantRepository enseignantSessionBean = new EnseignantRepository();
    UserRepository userSessionBean = new UserRepository();
    EcoleRepository ecoleSessionBean = new EcoleRepository();

    @GET
    @Path ("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        return userSessionBean.getAllUsers().toString();
    }

    @GET
    @Path("/enseignants")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EnseignantProfileInfoDto> getEnseignants() {
        List<EnseignantEntity> enseignants = enseignantSessionBean.getAllEnseignants();
        List<EnseignantProfileInfoDto> enseignantDtos = new ArrayList<>();
        for (EnseignantEntity enseignant : enseignants) {
            EnseignantProfileInfoDto enseignantDto = new EnseignantProfileInfoDto(enseignant);
            enseignantDtos.add(enseignantDto);
        }

        return enseignantDtos;
    }

    @GET
    @Path("/ecoles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EcoleProfileInfoDto> getEcoles() {
        List<EcoleEntity> ecoles = ecoleSessionBean.getAllEcoles();
        List<EcoleProfileInfoDto> ecoleDtos = new ArrayList<>();
        for (EcoleEntity ecole : ecoles) {
            EcoleProfileInfoDto ecoleDto = new EcoleProfileInfoDto(ecole);
            ecoleDtos.add(ecoleDto);
            }

        return ecoleDtos;
    }

    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("userId") long userId) {
        try {
            // Attempt to delete the user by userId
            userSessionBean.deleteUser(userSessionBean.getUserById(userId));
            // Return a success response
            return Response.status(Response.Status.OK).entity("User deleted successfully").build();
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete user: " + e.getMessage())
                    .build();
        }
    }

}
