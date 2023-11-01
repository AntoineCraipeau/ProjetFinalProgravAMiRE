package org.amire.progav_finalproj.services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.amire.progav_finalproj.dto.*;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.repositories.EcoleRepository;
import org.amire.progav_finalproj.repositories.EnseignantRepository;

import java.util.List;

@Stateless
public class PreferenceMatcherService {

    @EJB
    private EnseignantRepository enseignantRepository;
    @EJB
    private EcoleRepository ecoleRepository;

    public List<EnseignantListElementDto> getMatchingEnseignant(long idEcole) {
        List<EnseignantProfileInfoDto> enseignants = enseignantRepository.getAllEnseignants().stream().map(EnseignantProfileInfoDto::new).toList();

        EcoleProfileInfoDto ecole = new EcoleProfileInfoDto(ecoleRepository.getEcoleById(idEcole));

        return enseignants.stream().filter(enseignant -> {
            return enseignant.getCompetences().hasMatch(ecole.getCompetencesRequises()) &&
                    enseignant.getTypeDeContrat().hasMatch(ecole.getTypeDeContrat());
        })
        .map(enseignant -> enseignantRepository.getEnseignantById(enseignant.getIdEnseignant()))
        .map(enseignant -> new EnseignantListElementDto(enseignant, idEcole)).toList();
    }

    public List<EcoleListElementDto> getMatchingEcole(long idEnseignant) {
        List<EcoleProfileInfoDto> ecoles = ecoleRepository.getAllEcoles().stream().map(EcoleProfileInfoDto::new).toList();

        EnseignantProfileInfoDto enseignant = new EnseignantProfileInfoDto(enseignantRepository.getEnseignantById(idEnseignant));

        return ecoles.stream().filter(ecole -> {
            return ecole.getCompetencesRequises().hasMatch(enseignant.getCompetences()) &&
                    ecole.getTypeDeContrat().hasMatch(enseignant.getTypeDeContrat());
        })
        .map(ecole -> ecoleRepository.getEcoleById(ecole.getIdEcole()))
        .map(ecole -> new EcoleListElementDto(ecole, idEnseignant)).toList();
    }
}
