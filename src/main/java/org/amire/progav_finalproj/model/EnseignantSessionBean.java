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

    public void deleteEnseignantById(long id){
        Query q = em.createQuery("delete from EnseignantEntity e where e.idEnseignant = :id"); // Requête JPQL
        q.setParameter("id", id);
        q.executeUpdate();

        Query q2 = em.createQuery("delete from UserinfoEntity u where u.idEnseignant = :id"); // Requête JPQL
        q2.setParameter("id", id);
        q2.executeUpdate();
    }

}
