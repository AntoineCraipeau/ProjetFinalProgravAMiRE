package org.amire.progav_finalproj.repositories;

import org.amire.progav_finalproj.model.UserinfoEntity;
import org.amire.progav_finalproj.utils.UserTypes;

import java.util.List;

public interface IUserRepository {
    public List<UserinfoEntity> getAllUsers();
    public UserinfoEntity getUserById(long id);
    public UserinfoEntity getUserByLogin(String login);
    public void addUser(UserinfoEntity user);
    public void editUser(UserinfoEntity user);
    public void deleteUser(UserinfoEntity user);
    public UserTypes getUserTypeFromUserId(long id);
    public long resolveUserIdFromEnseignantId(long idEnseignant);
    public long resolveUserIdFromEcoleId(long idEcole);
}
