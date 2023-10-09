package org.amire.progav_finalproj.model;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class EcoleSessionBean {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    public List<EcoleEntity> getAllEcoles() {
        Query q = em.createQuery("select e from EcoleEntity e"); // Requête JPQL
        return q.getResultList();
    }

}
