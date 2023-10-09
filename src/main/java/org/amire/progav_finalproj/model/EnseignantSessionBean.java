package org.amire.progav_finalproj.model;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class EnseignantSessionBean {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    public List<EnseignantEntity> getAllEnseignants() {
        Query q = em.createQuery("select e from EnseignantEntity e"); // Requête JPQL
        return q.getResultList();
    }

}
