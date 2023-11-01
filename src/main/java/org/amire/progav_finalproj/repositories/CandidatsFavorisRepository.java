package org.amire.progav_finalproj.repositories;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.model.FavorisEnseignantEntity;
import org.amire.progav_finalproj.model.EcoleEntity;

import java.util.List;

@Stateless
public class CandidatsFavorisRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    // Read

    public List<EcoleEntity> getAllFavorisOfCandidatById(long idEnseignant){
        Query q = em.createQuery("select e from FavorisEnseignantEntity e where e.idEnseignant = :idEnseignant"); // Requête JPQL
        q.setParameter("idEnseignant", idEnseignant);
        List<FavorisEnseignantEntity> favoris = q.getResultList();
        //On extrait les écoles de la liste
        return favoris.stream().map(FavorisEnseignantEntity::getEcole).toList();
    }

    public FavorisEnseignantEntity getFavorisCandidatById(long id){
        Query q = em.createQuery("select e from FavorisEnseignantEntity e where e.idCandidatsFavoris = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (FavorisEnseignantEntity) q.getSingleResult();
    }

    public FavorisEnseignantEntity getFavorisCandidatByOwnersId(long idEnseignant, long idEcole){
        Query q = em.createQuery("select e from FavorisEnseignantEntity e where e.idEcole = :idEcole and e.idEnseignant = :idEnseignant"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        q.setParameter("idEnseignant", idEnseignant);
        return (FavorisEnseignantEntity) q.getSingleResult();
    }

    // Create

    public void addCandidatsFavoris(EnseignantEntity enseignant, EcoleEntity ecole){
        FavorisEnseignantEntity favoris = new FavorisEnseignantEntity();
        favoris.setEcole(ecole);
        favoris.setEnseignant(enseignant);
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

    public void removeCandidatsFavoris(FavorisEnseignantEntity favoris){
        em.getTransaction().begin();
        em.remove(favoris);
        em.getTransaction().commit();
    }

}