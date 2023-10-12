package org.amire.progav_finalproj.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.amire.progav_finalproj.utils.UserTypes;

import java.util.List;

@Stateless
public class UserRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    public List<UserinfoEntity> getAllUsers() {
        Query q = em.createQuery("select e from UserinfoEntity e"); // Requête JPQL
        return q.getResultList();
    }

    public UserinfoEntity getUserById(long id) {
        Query q = em.createQuery("select e from UserinfoEntity e where e.idUserinfo = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (UserinfoEntity) q.getSingleResult();
    }

    public UserinfoEntity getUserByLogin(String login) {
        Query q = em.createQuery("select e from UserinfoEntity e where e.login = :login"); // Requête JPQL
        q.setParameter("login", login);
        return (UserinfoEntity) q.getSingleResult();
    }

    public void editUser(UserinfoEntity user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void deleteUser(UserinfoEntity user) {
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    public UserTypes getUserTypeFromUserId(long id){
        UserinfoEntity user = getUserById(id);
        if (user.getIdEnseignant() != null) {
            return UserTypes.ENSEIGNANT;
        } else if (user.getIdEcole() != null) {
            return UserTypes.ECOLE;
        } else if (user.getIdAdmin() != null) {
            return UserTypes.ADMIN;
        } else {
            return UserTypes.NONE;
        }
    }
}
