package org.amire.progav_finalproj.repositories;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.amire.progav_finalproj.model.UserinfoEntity;
import org.amire.progav_finalproj.utils.UserTypes;

import java.util.List;

@Stateless
public class JpaUserRepository implements IUserRepository {

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
        try {
            return (UserinfoEntity) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void addUser(UserinfoEntity user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
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

    public long resolveUserIdFromEnseignantId(long enseignantId) {
        Query q = em.createQuery("select e from UserinfoEntity e where e.idEnseignant = :id"); // Requête JPQL
        q.setParameter("id", enseignantId);
        return ((UserinfoEntity) q.getSingleResult()).getIdUserinfo();
    }

    public long resolveUserIdFromEcoleId(long ecoleId) {
        Query q = em.createQuery("select e from UserinfoEntity e where e.idEcole = :id"); // Requête JPQL
        q.setParameter("id", ecoleId);
        return ((UserinfoEntity) q.getSingleResult()).getIdUserinfo();
    }
}
