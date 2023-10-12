package org.amire.progav_finalproj.repositories;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.amire.progav_finalproj.model.EcolesFavorisEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;

import java.util.List;

@Stateless
public class EcoleFavorisRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    public void addEcoleFavoris(long idEcole, long idEnseignant){
        EcolesFavorisEntity favoris = new EcolesFavorisEntity();
        favoris.setIdEcole(idEcole);
        favoris.setIdEnseignant(idEnseignant);
        em.getTransaction().begin();
        em.persist(favoris);
        em.getTransaction().commit();
    }

    public void removeEcoleFavoris(long idEcole, long idEnseignant){
        Query q = em.createQuery("delete from EcolesFavorisEntity e where e.idEcole = :idEcole and e.idEnseignant = :idEnseignant"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        q.setParameter("idEnseignant", idEnseignant);
        q.executeUpdate();
    }

    public List<EnseignantEntity> getAllFavorisOfEcoleById(long idEcole){
        Query q = em.createQuery("select e from EcolesFavorisEntity e where e.idEcole = :idEcole"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        List<EcolesFavorisEntity> favoris = q.getResultList();
        //On extrait les enseignants de la liste
        return favoris.stream().map(EcolesFavorisEntity::getEnseignantByIdEnseignant).toList();
    }

}
