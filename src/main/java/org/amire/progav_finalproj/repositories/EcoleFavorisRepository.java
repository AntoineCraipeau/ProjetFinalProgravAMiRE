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

    // Read

    public List<EnseignantEntity> getAllFavorisOfEcoleById(long idEcole){
        Query q = em.createQuery("select e from EcolesFavorisEntity e where e.idEcole = :idEcole"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        List<EcolesFavorisEntity> favoris = q.getResultList();
        //On extrait les enseignants de la liste
        return favoris.stream().map(EcolesFavorisEntity::getEnseignantByIdEnseignant).toList();
    }

    public EcolesFavorisEntity getFavorisEcoleById(long id){
        Query q = em.createQuery("select e from EcolesFavorisEntity e where e.idEcolesFavories = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (EcolesFavorisEntity) q.getSingleResult();
    }

    public EcolesFavorisEntity getFavorisEcoleByOwnersId(long idEcole, long idEnseignant){
        Query q = em.createQuery("select e from EcolesFavorisEntity e where e.idEcole = :idEcole and e.idEnseignant = :idEnseignant"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        q.setParameter("idEnseignant", idEnseignant);
        return (EcolesFavorisEntity) q.getSingleResult();
    }

    // Create

    public void addFavorisEcole(long idEcole, long idEnseignant){
        EcolesFavorisEntity favoris = new EcolesFavorisEntity();
        favoris.setIdEcole(idEcole);
        favoris.setIdEnseignant(idEnseignant);
        em.getTransaction().begin();
        em.persist(favoris);
        em.getTransaction().commit();
    }


    // Delete

    public void removeFavorisEcoleById(long id){
        removeFavorisEcole(getFavorisEcoleById(id));
    }

    public void removeFavorisEcoleByOwnersIds(long idEcole, long idEnseignant){
        removeFavorisEcole(getFavorisEcoleByOwnersId(idEcole, idEnseignant));
    }

    public void removeFavorisEcole(EcolesFavorisEntity favoris){
        em.getTransaction().begin();
        em.remove(favoris);
        em.getTransaction().commit();
    }

}
