package org.amire.progav_finalproj.model;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class EcoleRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    public List<EcoleEntity> getAllEcoles() {
        Query q = em.createQuery("select e from EcoleEntity e"); // Requête JPQL
        return q.getResultList();
    }

    public void deleteEcoleById(long id) { // Faut trouver un moyen de respecter l'aspect transactionnel non ?
        Query q = em.createQuery("delete from EcoleEntity e where e.idEcole = :id"); // Requête JPQL
        q.setParameter("id", id);
        q.executeUpdate();

        Query q2 = em.createQuery("delete from UserinfoEntity u where u.idEcole = :id"); // Requête JPQL
        q2.setParameter("id", id);
        q2.executeUpdate();
    }

}
