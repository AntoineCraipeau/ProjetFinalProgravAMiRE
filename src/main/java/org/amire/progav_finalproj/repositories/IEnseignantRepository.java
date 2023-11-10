package org.amire.progav_finalproj.repositories;

import org.amire.progav_finalproj.model.EnseignantEntity;

import java.util.List;

public interface IEnseignantRepository {
    public EnseignantEntity getEnseignantById(long id);
    public List<EnseignantEntity> getAllEnseignants();
    public void addEnseignant(EnseignantEntity enseignant);
    public void editEnseignant(EnseignantEntity enseignant);
    public void deleteEnseignantById(long userId);
}
