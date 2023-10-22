package org.amire.progav_finalproj.repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.amire.progav_finalproj.model.PostuleEntity;

import java.util.List;

@Stateless
public class PostuleRepository {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    // Read

    public List<PostuleEntity> getAllPostules() {
        Query q = em.createQuery("select e from PostuleEntity e"); // Requête JPQL
        return q.getResultList();
    }

    public List<PostuleEntity> getAllPostulesByEcoleId(long idEcole) {
        Query q = em.createQuery("select e from PostuleEntity e where e.idEcole = :idEcole"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        return q.getResultList();
    }

    public List<PostuleEntity> getAllPostulesByEnseignantId(long idEnseignant) {
        Query q = em.createQuery("select e from PostuleEntity e where e.idEnseignant = :idEnseignant"); // Requête JPQL
        q.setParameter("idEnseignant", idEnseignant);
        return q.getResultList();
    }

    public PostuleEntity getPostuleById(long id) {
        Query q = em.createQuery("select e from PostuleEntity e where e.idPostule = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (PostuleEntity) q.getSingleResult();
    }

    public PostuleEntity getPostuleByOwnersIds(long idEcole, long idEnseignant){
        Query q = em.createQuery("select e from PostuleEntity e where e.idEcole = :idEcole and e.idEnseignant = :idEnseignant"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        q.setParameter("idEnseignant", idEnseignant);
        return (PostuleEntity) q.getSingleResult();
    }

    // Create & Update

    public void addPostule(PostuleEntity postule) {
        em.getTransaction().begin();
        em.persist(postule);
        em.getTransaction().commit();
    }

    public void editPostule(PostuleEntity postule) {
        em.getTransaction().begin();
        em.merge(postule);
        em.getTransaction().commit();
    }

    // Delete

    public void removePostuleById(long id) {
        removePostule(getPostuleById(id));
    }

    public void removePostuleByOwnersIds(long idEcole, long idEnseignant){
        removePostule(getPostuleByOwnersIds(idEcole, idEnseignant));
    }

    public void removePostule(PostuleEntity postule) {
        em.getTransaction().begin();
        em.remove(postule);
        em.getTransaction().commit();
    }
}
