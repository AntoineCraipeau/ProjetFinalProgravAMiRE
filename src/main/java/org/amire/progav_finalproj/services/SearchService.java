package org.amire.progav_finalproj.services;

import jakarta.ejb.Stateless;
import org.amire.progav_finalproj.dto.EcoleListElementDto;
import org.amire.progav_finalproj.dto.EnseignantListElementDto;

import java.util.List;

@Stateless
public class SearchService implements ISearchService {

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
