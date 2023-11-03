package org.amire.progav_finalproj.services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.amire.progav_finalproj.dto.*;
import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.repositories.EcoleRepository;
import org.amire.progav_finalproj.repositories.EnseignantRepository;
import org.amire.progav_finalproj.repositories.PostuleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PreferenceMatcherService {

    @EJB
    private EnseignantRepository enseignantRepository;
    @EJB
    private EcoleRepository ecoleRepository;
    @EJB
    private PostuleRepository postuleRepository;

    public List<EnseignantListElementDto> filterEnseignantsByCompetence(List<EnseignantListElementDto> enseignants, Long idEcole) {
        EcoleProfileInfoDto ecole = new EcoleProfileInfoDto(ecoleRepository.getEcoleById(idEcole));
        return enseignants.stream().filter(enseignant -> {
            return enseignant.getCompetences().hasMatch(ecole.getCompetencesRequises());
        }).toList();
    }
    public List<EnseignantListElementDto> filterEnseignantsByTypeDeContrat(List<EnseignantListElementDto> enseignants, Long idEcole) {
        EcoleProfileInfoDto ecole = new EcoleProfileInfoDto(ecoleRepository.getEcoleById(idEcole));
        return enseignants.stream().filter(enseignant -> {
            return enseignant.getTypeDeContrat().hasMatch(ecole.getTypeDeContrat());
        }).toList();
    }
    public List<EnseignantListElementDto> filterEnseignantsAlreadyInCommonPostulation(List<EnseignantListElementDto> enseignants, Long idEcole) {
        EcoleEntity ecole = ecoleRepository.getEcoleById(idEcole);
        enseignants.removeIf(enseignant -> postuleRepository.getPostuleByOwnersIds(ecole.getIdEcole(), enseignant.getIdEnseignant()) != null);
        return enseignants;
    }

    public List<EnseignantListElementDto> getMatchingEnseignant(long idEcole) {
        List<EnseignantListElementDto> enseignants = enseignantRepository
                                                    .getAllEnseignants()
                                                    .stream()
                                                    .map(enseignantEntity -> new EnseignantListElementDto(enseignantEntity, idEcole))
                                                    .collect(Collectors.toCollection(ArrayList::new));
        //enseignants = filterEnseignantsByCompetence(enseignants, idEcole);
        //enseignants = filterEnseignantsByTypeDeContrat(enseignants, idEcole);
        enseignants = filterEnseignantsAlreadyInCommonPostulation(enseignants, idEcole);
        return enseignants;
    }


    public List<EcoleListElementDto> filterEcolesByCompetence(List<EcoleListElementDto> ecoles, Long idEnseignant) {
        EnseignantProfileInfoDto enseignant = new EnseignantProfileInfoDto(enseignantRepository.getEnseignantById(idEnseignant));
        return ecoles.stream().filter(ecole -> {
            return ecole.getCompetencesRequises().hasMatch(enseignant.getCompetences());
        }).toList();
    }
    public List<EcoleListElementDto> filterEcolesByTypeDeContrat(List<EcoleListElementDto> ecoles, Long idEnseignant) {
        EnseignantProfileInfoDto enseignant = new EnseignantProfileInfoDto(enseignantRepository.getEnseignantById(idEnseignant));
        return ecoles.stream().filter(ecole -> {
            return ecole.getTypeDeContrat().hasMatch(enseignant.getTypeDeContrat());
        }).toList();
    }
    public List<EcoleListElementDto> filterEcolesAlreadyInCommonPostulation(List<EcoleListElementDto> ecoles, Long idEnseignant) {
        EnseignantEntity enseignant = enseignantRepository.getEnseignantById(idEnseignant);
        ecoles.removeIf(ecole -> postuleRepository.getPostuleByOwnersIds(ecole.getIdEcole(), enseignant.getIdEnseignant()) != null);
        return ecoles;
    }
    public List<EcoleListElementDto> getMatchingEcole(long idEnseignant) {
        List<EcoleListElementDto> ecoles = ecoleRepository
                                            .getAllEcoles()
                                            .stream()
                                            .map(ecoleEntity -> new EcoleListElementDto(ecoleEntity, idEnseignant))
                                            .collect(Collectors.toCollection(ArrayList::new));
        //ecoles = filterEcolesByCompetence(ecoles, idEnseignant);
        //ecoles = filterEcolesByTypeDeContrat(ecoles, idEnseignant);
        ecoles = filterEcolesAlreadyInCommonPostulation(ecoles, idEnseignant);
        return ecoles;
    }
}
