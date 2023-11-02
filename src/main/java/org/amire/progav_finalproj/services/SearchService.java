package org.amire.progav_finalproj.services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.repositories.EcoleRepository;
import org.amire.progav_finalproj.repositories.EnseignantRepository;

import java.util.List;

@Stateless
public class SearchService {

    @EJB
    private EnseignantRepository enseignantRepository;
    @EJB
    private EcoleRepository ecoleRepository;

    public List<EnseignantEntity> searchAsEcole(String searchQuery) {
        List<EnseignantEntity> enseignants = enseignantRepository.getAllEnseignants();
        if (searchQuery == null) return enseignants;
        if (searchQuery.isBlank()) return enseignants;
        enseignants.removeIf(enseignant ->
                !enseignant.getNom().contains(searchQuery) &&
                !enseignant.getPrenom().contains(searchQuery) &&
                !enseignant.getCompetences().contains(searchQuery) &&
                !enseignant.getTypeDeContrat().contains(searchQuery)
        );
        return enseignants;
    }

    public List<EcoleEntity> searchAsEnseignant(String searchQuery) {
        List<EcoleEntity> ecoles = ecoleRepository.getAllEcoles();
        if (searchQuery == null) return ecoles;
        if (searchQuery.isBlank()) return ecoles;
        ecoles.removeIf(ecole ->
                !ecole.getRaisonSociale().contains(searchQuery) &&
                !ecole.getCompetencesRequises().contains(searchQuery) &&
                !ecole.getTypeDeContrat().contains(searchQuery)
        );
        return ecoles;
    }
}
