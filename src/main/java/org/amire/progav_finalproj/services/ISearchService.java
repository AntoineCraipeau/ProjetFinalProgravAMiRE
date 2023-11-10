package org.amire.progav_finalproj.services;

import org.amire.progav_finalproj.dto.EcoleListElementDto;
import org.amire.progav_finalproj.dto.EnseignantListElementDto;

import java.util.List;

public interface ISearchService {
    public List<EnseignantListElementDto> filterAsEcole(List<EnseignantListElementDto> enseignants, String searchQuery);
    public List<EcoleListElementDto> filterAsEnseignant(List<EcoleListElementDto> ecoles, String searchQuery);
}
