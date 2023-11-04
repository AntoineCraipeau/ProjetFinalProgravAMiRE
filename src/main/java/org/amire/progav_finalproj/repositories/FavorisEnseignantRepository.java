package org.amire.progav_finalproj.repositories;


import jakarta.ejb.EJB;
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
public class FavorisEnseignantRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();
    @EJB
    EcoleRepository ecoleRepository;

    // Read

    public List<EcoleEntity> getAllFavorisOfCandidatById(long idEnseignant){
        Query q = em.createQuery("select e from FavorisEnseignantEntity e where e.idEnseignant = :idEnseignant"); // Requête JPQL
        q.setParameter("idEnseignant", idEnseignant);
        List<FavorisEnseignantEntity> favoris = q.getResultList();
        List<Long> favorisIds = favoris.stream().map(FavorisEnseignantEntity::getIdEcole).toList();

        List<EcoleEntity> ecoles = ecoleRepository.getAllEcoles();
        ecoles.removeIf(ecole -> !favorisIds.contains(ecole.getIdEcole()));

        return ecoles;
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
        try {
            return (FavorisEnseignantEntity) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Create

    public void addCandidatsFavoris(EnseignantEntity enseignant, EcoleEntity ecole){
        if(getFavorisCandidatByOwnersId(enseignant.getIdEnseignant(), ecole.getIdEcole()) != null) {
            return;
        }
        FavorisEnseignantEntity favoris = new FavorisEnseignantEntity();
        favoris.setEcole(ecole);
        favoris.setEnseignant(enseignant);
        favoris.setIdEnseignant(enseignant.getIdEnseignant());
        favoris.setIdEcole(ecole.getIdEcole());
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