package org.amire.progav_finalproj.repositories;


import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.FavorisEcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;

import java.util.List;

@Stateless
public class JpaFavorisEcoleRepository implements IFavorisEcoleRepository {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();
    @EJB
    IEnseignantRepository enseignantRepository;

    // Read

    public List<EnseignantEntity> getAllFavorisOfEcoleById(long idEcole){
        Query q = em.createQuery("select e from FavorisEcoleEntity e where e.idEcole = :idEcole"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        List<FavorisEcoleEntity> favoris = q.getResultList();
        List<Long> favorisIds = favoris.stream().map(FavorisEcoleEntity::getIdEnseignant).toList();

        List<EnseignantEntity> enseignants = enseignantRepository.getAllEnseignants();
        enseignants.removeIf(enseignant -> !favorisIds.contains(enseignant.getIdEnseignant()));

        return enseignants;
    }

    public List<FavorisEcoleEntity> getAllFavorisOfEcoleByIdAsFavoris(long idEcole){
        Query q = em.createQuery("select e from FavorisEcoleEntity e where e.idEcole = :idEcole"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        return q.getResultList();
    }

    public FavorisEcoleEntity getFavorisEcoleById(long id){
        Query q = em.createQuery("select e from FavorisEcoleEntity e where e.idEcolesFavories = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (FavorisEcoleEntity) q.getSingleResult();
    }

    public FavorisEcoleEntity getFavorisEcoleByOwnersId(long idEcole, long idEnseignant){
        Query q = em.createQuery("select e from FavorisEcoleEntity e where e.idEcole = :idEcole and e.idEnseignant = :idEnseignant"); // Requête JPQL
        q.setParameter("idEcole", idEcole);
        q.setParameter("idEnseignant", idEnseignant);
        try {
            return (FavorisEcoleEntity) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    // Create

    public void addFavorisEcole(EcoleEntity ecole, EnseignantEntity enseignant){
        if(getFavorisEcoleByOwnersId(ecole.getIdEcole(), enseignant.getIdEnseignant()) != null) {
            return;
        }
        FavorisEcoleEntity favoris = new FavorisEcoleEntity();
        favoris.setEcole(ecole);
        favoris.setEnseignant(enseignant);
        favoris.setIdEcole(ecole.getIdEcole());
        favoris.setIdEnseignant(enseignant.getIdEnseignant());
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

    public void removeFavorisEcole(FavorisEcoleEntity favoris){
        em.getTransaction().begin();
        em.remove(favoris);
        em.getTransaction().commit();
    }

    public void removeAllFavorisEcoleByEcoleId(long idEcole){
        List<FavorisEcoleEntity> favoris = getAllFavorisOfEcoleByIdAsFavoris(idEcole);
        for (FavorisEcoleEntity favori : favoris) {
            removeFavorisEcole(favori);
        }
    }

}
