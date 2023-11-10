package org.amire.progav_finalproj;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.repositories.JpaEnseignantRepository;
import org.amire.progav_finalproj.repositories.JpaUserRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.*;


@Path("/employee")
public class ApiEmployee {

    JpaEnseignantRepository enseignantSessionBean = new JpaEnseignantRepository();
    JpaUserRepository userSessionBean = new JpaUserRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        return userSessionBean.getAllUsers().toString();
    }

    @GET
    @Path("/disponibilites")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Date> getDisponibilites() {
        List<EnseignantEntity> enseignants = enseignantSessionBean.getAllEnseignants();

        List<Date> dispos = enseignants.stream()
                .map(EnseignantEntity::getDateDebutDispo)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        return dispos;
    }

    @GET
    @Path("/enseignants-par-mois")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getEnseignantsParMois() {
        List<Date> dispos = getDisponibilites();

        // Créer un TreeMap pour stocker les données triées par mois
        Map<String, Integer> enseignantsParMois = new TreeMap<>();

        // Formateur de date pour extraire le mois avec la locale en_US
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", new Locale("en_US"));

        for (Date dispo : dispos) {
            String mois = monthFormat.format(dispo);
            enseignantsParMois.put(mois, enseignantsParMois.getOrDefault(mois, 0) + 1);
        }

        return enseignantsParMois;
    }


    @GET
    @Path("/EnseignantName")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEnseignantById(long id) {
        return userSessionBean.getUserById(id).toString();
    }
}
