package org.amire.progav_finalproj.services;

import org.amire.progav_finalproj.dto.EcoleListElementDto;
import org.amire.progav_finalproj.dto.EnseignantListElementDto;

import java.util.List;

public interface IPreferenceMatcherService {

    public List<EnseignantListElementDto> getMatchingEnseignant(long idEcole);
    public List<EcoleListElementDto> getMatchingEcole(long idEnseignant);

}
