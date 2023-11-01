package org.amire.progav_finalproj;

import jakarta.persistence.Query;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.amire.progav_finalproj.dto.PostuleProjection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.*;
import java.util.stream.Collectors;


@Path("/postule")
public class ApiPostule {
    EntityManagerFactory entityManagerFactory = jakarta.persistence.Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PostuleProjection> getAllPostule() {
        Query q = em.createQuery("SELECT NEW org.amire.progav_finalproj.dto.PostuleProjection(e.idPostule, e.dateCreation, e.decision, e.idEcole, e.idEnseignant) FROM PostuleEntity e");
        List<PostuleProjection> postules = q.getResultList();

        // Vérifier et remplacer les valeurs null de decision
        for (PostuleProjection postule : postules) {
            if (postule.getDecision() == null) {
                // Remplacez la valeur null par une valeur par défaut ou une chaîne vide, selon vos besoins.
                postule.setDecision("En attente"); // Vous pouvez utiliser la valeur souhaitée ici.
            }
        } q.getResultList();

        return postules;
    }

    @GET
    @Path("/decisions")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getDecisionsByResponse() {
        List<PostuleProjection> postules = getAllPostule();
        List<String> decisions = postules.stream()
                .map(PostuleProjection::getDecision)
                .collect(Collectors.toList());

        // Créer un TreeMap pour stocker les données triées par type de réponse
        Map<String, Integer> decisionByResponse = new TreeMap<>();

        for (String decision : decisions) {
            String response = decision;
            decisionByResponse.put(response, decisionByResponse.getOrDefault(response, 0) + 1);
        }

        return decisionByResponse;
    }
}
