package org.amire.progav_finalproj.repositories;

import org.amire.progav_finalproj.model.EcoleEntity;

import java.util.List;

public interface IEcoleRepository {
    public EcoleEntity getEcoleById(long id);
    public List<EcoleEntity> getAllEcoles();
    public void addEcole(EcoleEntity ecole);
    public void editEcole(EcoleEntity ecole);
    public void deleteEcoleById(long userId);
}
