package org.amire.progav_finalproj.factories;

import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.model.PostuleEntity;

public class PostuleFactory {

    public static PostuleEntity buildPostule(EnseignantEntity enseignant, EcoleEntity ecole){
        PostuleEntity postule = new PostuleEntity();
        postule.setDecision("En attente");
        postule.setDate(new java.sql.Timestamp(System.currentTimeMillis()));
        postule.setEnseignantByIdEnseignant(enseignant);
        postule.setIdEnseignant(enseignant.getIdEnseignant());
        postule.setEcoleByIdEcole(ecole);
        postule.setIdEcole(ecole.getIdEcole());
        return postule;
    }

}
