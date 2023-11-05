package org.amire.progav_finalproj.services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.amire.progav_finalproj.dto.EcoleListElementDto;
import org.amire.progav_finalproj.dto.EnseignantListElementDto;
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
                !enseignant.getNom().toLowerCase().contains(searchQuery.toLowerCase()) &&
                !enseignant.getPrenom().toLowerCase().contains(searchQuery.toLowerCase()) &&
                !enseignant.getCompetences().toLowerCase().contains(searchQuery.toLowerCase()) &&
                !enseignant.getTypeDeContrat().toLowerCase().contains(searchQuery.toLowerCase())
        );
        return enseignants;
    }

    public List<EnseignantListElementDto> filterAsEcole(List<EnseignantListElementDto> enseignants, String searchQuery) {
        if (searchQuery == null) return enseignants;
        if (searchQuery.isBlank()) return enseignants;
        enseignants.removeIf(enseignant ->
                !enseignant.getNom().toLowerCase().contains(searchQuery.toLowerCase()) &&
                !enseignant.getPrenom().toLowerCase().contains(searchQuery.toLowerCase()) &&
                !enseignant.getCompetenceText().toLowerCase().contains(searchQuery.toLowerCase()) &&
                !enseignant.getContratText().toLowerCase().contains(searchQuery.toLowerCase())
        );
        return enseignants;
    }

    public List<EcoleEntity> searchAsEnseignant(String searchQuery) {
        List<EcoleEntity> ecoles = ecoleRepository.getAllEcoles();
        if (searchQuery == null) return ecoles;
        if (searchQuery.isBlank()) return ecoles;
        ecoles.removeIf(ecole ->
                !ecole.getRaisonSociale().toLowerCase().contains(searchQuery.toLowerCase()) &&
                !ecole.getCompetencesRequises().toLowerCase().contains(searchQuery.toLowerCase()) &&
                !ecole.getTypeDeContrat().toLowerCase().contains(searchQuery.toLowerCase())
        );
        return ecoles;
    }

    public List<EcoleListElementDto> filterAsEnseignant(List<EcoleListElementDto> ecoles, String searchQuery) {
        if (searchQuery == null) return ecoles;
        if (searchQuery.isBlank()) return ecoles;
        ecoles.removeIf(ecole ->
                !ecole.getRaisonSociale().toLowerCase().contains(searchQuery.toLowerCase()) &&
                !ecole.getCompetenceText().toLowerCase().contains(searchQuery.toLowerCase()) &&
                !ecole.getContratText().toLowerCase().contains(searchQuery.toLowerCase())
        );
        return ecoles;
    }
}
