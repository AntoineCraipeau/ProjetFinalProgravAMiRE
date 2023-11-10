package org.amire.progav_finalproj;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

import org.amire.progav_finalproj.dto.EnseignantApiDto;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.dto.EcoleApiDto;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.repositories.EnseignantRepository;
import org.amire.progav_finalproj.repositories.EcoleRepository;
import org.amire.progav_finalproj.repositories.UserRepository;
import org.amire.progav_finalproj.utils.UserTypes;

import java.text.SimpleDateFormat;
import java.util.*;


@Path("/users")
public class ApiUsers {

    EnseignantRepository enseignantSessionBean = new EnseignantRepository();
    UserRepository userSessionBean = new UserRepository();
    EcoleRepository ecoleSessionBean = new EcoleRepository();
    UserTypes userType;

    @GET
    @Path ("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        return userSessionBean.getAllUsers().toString();
    }

    @GET
    @Path("/enseignants")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EnseignantApiDto> getEnseignants() {
        List<EnseignantEntity> enseignants = enseignantSessionBean.getAllEnseignants();
        List<EnseignantApiDto> enseignantDtos = new ArrayList<>();
        for (EnseignantEntity enseignant : enseignants) {
            EnseignantApiDto enseignantDto = new EnseignantApiDto(enseignant);
            enseignantDtos.add(enseignantDto);
        }

        return enseignantDtos;
    }

    @GET
    @Path("/ecoles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EcoleApiDto> getEcoles() {
        List<EcoleEntity> ecoles = ecoleSessionBean.getAllEcoles();
        List<EcoleApiDto> ecoleDtos = new ArrayList<>();
        for (EcoleEntity ecole : ecoles) {
            EcoleApiDto ecoleDto = new EcoleApiDto(ecole);
            ecoleDtos.add(ecoleDto);
            }

        return ecoleDtos;
    }

    @DELETE
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEnseignant(@PathParam("userId") long userId) {
        try {
            if(userSessionBean.getUserTypeFromUserId(userId) == userType.ENSEIGNANT){
                // Attempt to delete the user by userId
                enseignantSessionBean.deleteEnseignantById(userId);
                //userSessionBean.deleteUser(userSessionBean.getUserById(userId));
            }
            else if(userSessionBean.getUserTypeFromUserId(userId) == userType.ECOLE){
                ecoleSessionBean.deleteEcoleById(userId);
                //userSessionBean.deleteUser(userSessionBean.getUserById(userId));
            }
            else{
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Failed to delete user: " + userId + "\nUser is not an enseignant or an ecole")
                        .build();
            }
            // Return a successful response
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
