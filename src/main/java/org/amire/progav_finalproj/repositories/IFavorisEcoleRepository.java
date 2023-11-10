package org.amire.progav_finalproj.repositories;

import org.amire.progav_finalproj.model.EcoleEntity;
import org.amire.progav_finalproj.model.EnseignantEntity;
import org.amire.progav_finalproj.model.FavorisEcoleEntity;

import java.util.List;

public interface IFavorisEcoleRepository {
    public List<EnseignantEntity> getAllFavorisOfEcoleById(long idEcole);
    public List<FavorisEcoleEntity> getAllFavorisOfEcoleByIdAsFavoris(long idEcole);
    public FavorisEcoleEntity getFavorisEcoleById(long id);
    public FavorisEcoleEntity getFavorisEcoleByOwnersId(long idEcole, long idEnseignant);
    public void addFavorisEcole(EcoleEntity ecole, EnseignantEntity enseignant);
    public void removeFavorisEcoleById(long id);
    public void removeFavorisEcoleByOwnersIds(long idEcole, long idEnseignant);
    public void removeFavorisEcole(FavorisEcoleEntity favorisEcole);
    public void removeAllFavorisEcoleByEcoleId(long idEcole);

}
