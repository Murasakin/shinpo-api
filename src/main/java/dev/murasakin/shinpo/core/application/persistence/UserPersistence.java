package dev.murasakin.shinpo.core.application.persistence;

import dev.murasakin.shinpo.core.application.model.user.UserUpdateModel;
import dev.murasakin.shinpo.core.application.model.user.UserCreateModel;
import dev.murasakin.shinpo.core.application.model.user.UserReadModel;
import jakarta.annotation.Nullable;

import java.util.List;

public interface UserPersistence {
    void saveUser(UserCreateModel user);
    List<UserReadModel> retrieveAllUsers();
    @Nullable UserReadModel retrieveByUsername(String username);
    void updateUser(String username, UserUpdateModel user);
    void deleteUser(String username);
    boolean usernameAlreadyExists(String username);
}
