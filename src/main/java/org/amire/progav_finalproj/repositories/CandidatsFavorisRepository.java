package org.amire.progav_finalproj.repositories;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.amire.progav_finalproj.model.CandidatsFavorisEntity;
import org.amire.progav_finalproj.model.EcoleEntity;

import java.util.List;

@Stateless
public class CandidatsFavorisRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    // Read

    public List<EcoleEntity> getAllFavorisOfCandidatById(long idEnseignant){
        Query q = em.createQuery("select e from CandidatsFavorisEntity e where e.idEnseignant = :idEnseignant"); // Requête JPQL
        q.setParameter("idEnseignant", idEnseignant);
        List<CandidatsFavorisEntity> favoris = q.getResultList();
        //On extrait les écoles de la liste
        return favoris.stream().map(CandidatsFavorisEntity::getEcoleByIdEcole).toList();
    }

    public CandidatsFavorisEntity getFavorisCandidatById(long id){
        Query q = em.createQuery("select e from CandidatsFavorisEntity e where e.idCandidatsFavoris = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (CandidatsFavorisEntity) q.getSingleResult();
    }

    public CandidatsFavorisEntity getFavorisCandidatByOwnersId(long idEnseignant, long idEcole){
        Query q = em.createQuery("select e from CandidatsFavorisEntity e where e.idEcole = :idEcole and e.idEnseignant = :idEnseignant"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        q.setParameter("idEnseignant", idEnseignant);
        return (CandidatsFavorisEntity) q.getSingleResult();
    }

    // Create

    public void addCandidatsFavoris(long idEnseignant, long idEcole){
        CandidatsFavorisEntity favoris = new CandidatsFavorisEntity();
        favoris.setIdEcole(idEcole);
        favoris.setIdEnseignant(idEnseignant);
        em.getTransaction().begin();
        em.persist(favoris);
        em.getTransaction().commit();
    }

    // Delete

    public void removeCandidatsFavorisById(long id){
        removeCandidatsFavoris(getFavorisCandidatById(id));
    }

    public void removeCandidatsFavorisByOwnersId(long idEnseignant, long idEcole){
        removeCandidatsFavoris(getFavorisCandidatByOwnersId(idEnseignant, idEcole));
    }

    public void removeCandidatsFavoris(CandidatsFavorisEntity favoris){
        em.getTransaction().begin();
        em.remove(favoris);
        em.getTransaction().commit();
    }

}