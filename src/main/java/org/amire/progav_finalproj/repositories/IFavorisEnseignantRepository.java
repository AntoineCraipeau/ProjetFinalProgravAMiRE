package org.amire.progav_finalproj.repositories;

import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.model.FavorisEnseignantEntity;

import java.util.List;

public interface IFavorisEnseignantRepository {
    public List<EcoleEntity> getAllFavorisOfCandidatById(long idEnseignant);
    public List<FavorisEnseignantEntity> getAllFavorisOfCandidatByIdAsFavoris(long idEnseignant);
    public FavorisEnseignantEntity getFavorisCandidatById(long id);
    public FavorisEnseignantEntity getFavorisCandidatByOwnersId(long idEnseignant, long idEcole);
    public void addCandidatsFavoris(EnseignantEntity enseignant, EcoleEntity ecole);
    public void removeCandidatsFavorisById(long id);
    public void removeCandidatsFavorisByOwnersId(long idEnseignant, long idEcole);
    public void removeCandidatsFavoris(FavorisEnseignantEntity favorisEnseignant);
    public void removeAllCandidatsFavorisByEnseignantId(long idEnseignant);
}
