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

    public List<PostuleEntity> getAllPostules() {
        Query q = em.createQuery("select e from PostuleEntity e"); // Requête JPQL
        return q.getResultList();
    }

    public PostuleEntity getPostuleById(long id) {
        Query q = em.createQuery("select e from PostuleEntity e where e.idPostule = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (PostuleEntity) q.getSingleResult();
    }

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

    public void removePostuleById(long id) {
        removePostule(getPostuleById(id));
    }

    public void removePostule(PostuleEntity postule) {
        em.getTransaction().begin();
        em.remove(postule);
        em.getTransaction().commit();
    }
}
