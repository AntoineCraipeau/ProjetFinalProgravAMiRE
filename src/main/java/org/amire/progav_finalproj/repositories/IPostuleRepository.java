package org.amire.progav_finalproj.repositories;

import org.amire.progav_finalproj.model.PostuleEntity;

import java.util.List;

public interface IPostuleRepository {
    public List<PostuleEntity> getAllPostules();
    public List<PostuleEntity> getAllPostulesByEcoleId(long idEcole);
    public List<PostuleEntity> getAllPostulesByEnseignantId(long idEnseignant);
    public PostuleEntity getPostuleById(long id);
    public PostuleEntity getPostuleByOwnersIds(long idEcole, long idEnseignant);
    public void addPostule(PostuleEntity postule);
    public void editPostule(PostuleEntity postule);
    public void removePostuleById(long id);
    public void removePostuleByOwnersIds(long idEcole, long idEnseignant);
    public void removePostule(PostuleEntity postule);
    public void removeAllPostulesByEcoleId(long idEcole);
    public void removeAllPostulesByEnseignantId(long idEnseignant);

}
