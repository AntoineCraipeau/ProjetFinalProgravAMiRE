package org.amire.progav_finalproj.repositories;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.amire.progav_finalproj.model.EcoleEntity;

import java.util.List;

@Stateless
public class EcoleRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    // Read

    public EcoleEntity getEcoleById(long id) {
        Query q = em.createQuery("select e from EcoleEntity e where e.idEcole = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (EcoleEntity) q.getSingleResult();
    }

    public List<EcoleEntity> getAllEcoles() {
        Query q = em.createQuery("select e from EcoleEntity e"); // Requête JPQL
        return q.getResultList();
    }

    // Create

    public void addEcole(EcoleEntity ecole) {
        em.getTransaction().begin();
        em.persist(ecole);
        em.getTransaction().commit();
    }

    // Update

    public void editEcole(EcoleEntity ecole) {
        em.getTransaction().begin();
        em.merge(ecole);
        em.getTransaction().commit();
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
