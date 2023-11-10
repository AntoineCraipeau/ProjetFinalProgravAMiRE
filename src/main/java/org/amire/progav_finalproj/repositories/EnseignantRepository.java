package org.amire.progav_finalproj.repositories;


import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.model.UserinfoEntity;

import java.util.List;

@Stateless
public class EnseignantRepository implements IEnseignantRepository {

    @EJB
    private IUserRepository userRepository;
    @EJB
    private IPostuleRepository postuleRepository;
    @EJB
    private IFavorisEnseignantRepository favorisEnseignantRepository;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    // Read

    public EnseignantEntity getEnseignantById(long id) {
        Query q = em.createQuery("select e from EnseignantEntity e where e.idEnseignant = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (EnseignantEntity) q.getSingleResult();
    }

    public List<EnseignantEntity> getAllEnseignants() {
        Query q = em.createQuery("select e from EnseignantEntity e"); // Requête JPQL
        return q.getResultList();
    }

    public void addEnseignant(EnseignantEntity enseignant) {
        em.getTransaction().begin();
        em.persist(enseignant);
        em.getTransaction().commit();
    }

    public void editEnseignant(EnseignantEntity enseignant) {
        em.getTransaction().begin();
        em.merge(enseignant);
        em.getTransaction().commit();
    }

    public void deleteEnseignantById(long userId){

        UserinfoEntity userinfo = userRepository.getUserById(userId);
        EnseignantEntity enseignant = getEnseignantById(userinfo.getIdEnseignant());

        postuleRepository.removeAllPostulesByEnseignantId(enseignant.getIdEnseignant());
        favorisEnseignantRepository.removeAllCandidatsFavorisByEnseignantId(enseignant.getIdEnseignant());

        em.getTransaction().begin();
        em.remove(enseignant);
        em.getTransaction().commit();

        userRepository.deleteUser(userinfo);

    }

}
