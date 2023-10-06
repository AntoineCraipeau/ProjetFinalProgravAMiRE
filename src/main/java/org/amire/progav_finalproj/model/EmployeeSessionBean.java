package org.amire.progav_finalproj.model;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

@Stateless
public class EmployeeSessionBean {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();


    public List<EmployesEntity> getAllEmployees() {
        Query q = em.createQuery("select e from EmployesEntity e"); // Requête JPQL
        return q.getResultList();
    }

    public EmployesEntity getEmployeeById(int id) {
        Query q = em.createQuery("select e from EmployesEntity e where e.id = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (EmployesEntity) q.getSingleResult();
    }

    public void editEmployee(EmployesEntity employe) {
        em.getTransaction().begin();
        em.merge(employe);
        em.getTransaction().commit();
    }

    public void deleteEmployee(EmployesEntity employe) {
        em.getTransaction().begin();
        em.remove(employe);
        em.getTransaction().commit();
    }

}
