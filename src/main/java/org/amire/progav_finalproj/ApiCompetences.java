package org.amire.progav_finalproj;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.repositories.EnseignantRepository;
import org.amire.progav_finalproj.repositories.EcoleRepository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Path("/competences")
public class ApiCompetences {
    EnseignantRepository enseignantSessionBean = new EnseignantRepository();
    EcoleRepository ecoleSessionBean = new EcoleRepository();

    @GET
    @Path("/competences-enseignants")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCompetencesEnseignants() {
        List<EnseignantEntity> enseignants = enseignantSessionBean.getAllEnseignants();

        List<String> competencesIndividuelles = new ArrayList<>();
        Matcher match = null;

        for (EnseignantEntity enseignant : enseignants) {
            Pattern competences = Pattern.compile("\\w+");
            match = competences.matcher(enseignant.getCompetences());
            while (match.find()) {
                competencesIndividuelles.add(match.group());
            }
        }

        return competencesIndividuelles;
    }

    @GET
    @Path("/enseignant-par-competence")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getEnseignantParComp() {
        List<String> comps = getCompetencesEnseignants();

        // Créer un TreeMap pour stocker les données triées par mois
        Map<String, Integer> enseignantsParComp = new TreeMap<>();

        for (String comp : comps) {
            enseignantsParComp.put(comp, enseignantsParComp.getOrDefault(comp, 0) + 1);
        }

        return enseignantsParComp;
    }

    @GET
    @Path("/competences-ecoles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getCompetencesEcoles() {
        List<EcoleEntity> ecoles = ecoleSessionBean.getAllEcoles();

        List<String> competencesIndividuelles = new ArrayList<>();
        Matcher match = null;

        for (EcoleEntity ecole : ecoles) {
            Pattern competences = Pattern.compile("\\w+");
            match = competences.matcher(ecole.getCompetencesRequises());
            while (match.find()) {
                competencesIndividuelles.add(match.group());
            }
        }

        return competencesIndividuelles;
    }

    @GET
    @Path("/ecole-par-competence")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getEcoleParComp() {
        List<String> comps = getCompetencesEcoles();

        // Créer un TreeMap pour stocker les données triées par mois
        Map<String, Integer> ecoleParComp = new TreeMap<>();

        for (String comp : comps) {
            ecoleParComp.put(comp, ecoleParComp.getOrDefault(comp, 0) + 1);
        }

        return ecoleParComp;
    }

}
