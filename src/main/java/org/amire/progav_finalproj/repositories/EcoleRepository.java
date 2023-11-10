package org.amire.progav_finalproj.repositories;


import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.UserinfoEntity;

import java.util.List;

@Stateless
public class EcoleRepository implements IEcoleRepository {

    @EJB
    private IUserRepository userRepository;
    @EJB
    private IPostuleRepository postuleRepository;
    @EJB
    private IFavorisEcoleRepository favorisEcoleRepository;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
    EntityManager em = entityManagerFactory.createEntityManager();

    // Read

    public EcoleEntity getEcoleById(long id) {
        Query q = em.createQuery("select e from EcoleEntity e where e.idEcole = :id"); // Requête JPQL
        q.setParameter("id", id);
        return (EcoleEntity) q.getSingleResult();
    }

    public List<EcoleEntity> getAllEcoles() {
        Query q = em.createQuery("select e from EcoleEntity e"); // Requête JPQL
        return q.getResultList();
    }

    // Create

    public void addEcole(EcoleEntity ecole) {
        em.getTransaction().begin();
        em.persist(ecole);
        em.getTransaction().commit();
    }

    // Update

    public void editEcole(EcoleEntity ecole) {
        em.getTransaction().begin();
        em.merge(ecole);
        em.getTransaction().commit();
    }

    public void deleteEcoleById(long userId) { // Faut trouver un moyen de respecter l'aspect transactionnel non ?

        UserinfoEntity userinfo = userRepository.getUserById(userId);
        EcoleEntity ecole = getEcoleById(userinfo.getIdEcole());

        postuleRepository.removeAllPostulesByEcoleId(ecole.getIdEcole());
        favorisEcoleRepository.removeAllFavorisEcoleByEcoleId(ecole.getIdEcole());

        em.getTransaction().begin();
        em.remove(ecole);
        em.getTransaction().commit();

        userRepository.deleteUser(userRepository.getUserById(userId));
    }

}
