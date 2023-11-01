package org.amire.progav_finalproj.repositories;


import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import org.amire.progav_finalproj.model.EnseignantEntity;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateless
public class EnseignantRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    // Read

    public EnseignantEntity getEnseignantById(long id) {
        Query q = em.createQuery("select e from EnseignantEntity e where e.idEnseignant = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (EnseignantEntity) q.getSingleResult();
    }

    public List<EnseignantEntity> getAllEnseignants() {
        Query q = em.createQuery("select e from EnseignantEntity e"); // Requête JPQL
        return q.getResultList();
    }

    public void addEnseignant(EnseignantEntity enseignant) {
        em.getTransaction().begin();
        em.persist(enseignant);
        em.getTransaction().commit();
    }

    public void editEnseignant(EnseignantEntity enseignant) {
        em.getTransaction().begin();
        em.merge(enseignant);
        em.getTransaction().commit();
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
