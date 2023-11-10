package org.amire.progav_finalproj;

import jakarta.ejb.EJB;
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
import org.amire.progav_finalproj.repositories.IEnseignantRepository;
import org.amire.progav_finalproj.repositories.IEcoleRepository;
import org.amire.progav_finalproj.repositories.IUserRepository;
import org.amire.progav_finalproj.utils.UserTypes;

import java.text.SimpleDateFormat;
import java.util.*;


@Path("/users")
public class ApiUsers {

    @EJB
    private IUserRepository userRepository;
    @EJB
    private IEnseignantRepository enseignantRepository;
    @EJB
    private IEcoleRepository ecoleRepository;
    UserTypes userType;

    @GET
    @Path ("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        return userRepository.getAllUsers().toString();
    }

    @GET
    @Path("/enseignants")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EnseignantApiDto> getEnseignants() {
        List<EnseignantEntity> enseignants = enseignantRepository.getAllEnseignants();
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
        List<EcoleEntity> ecoles = ecoleRepository.getAllEcoles();
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
            if(userRepository.getUserTypeFromUserId(userId) == userType.ENSEIGNANT){
                // Attempt to delete the user by userId
                enseignantRepository.deleteEnseignantById(userId);
                //userSessionBean.deleteUser(userSessionBean.getUserById(userId));
            }
            else if(userRepository.getUserTypeFromUserId(userId) == userType.ECOLE){
                ecoleRepository.deleteEcoleById(userId);
                //userSessionBean.deleteUser(userSessionBean.getUserById(userId));
            }
            else{
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Failed to delete user: " + userId + "\nUser is not an enseignant or an ecole")
                        .build();
            }
            // Return a successful response with JSON
            return Response.status(Response.Status.OK)
                    .entity("{\"message\": \"User deleted successfully\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error response
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to delete user: " + e.getMessage())
                    .build();
        }
    }
}
