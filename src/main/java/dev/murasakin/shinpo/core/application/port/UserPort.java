package dev.murasakin.shinpo.core.application.port;

import dev.murasakin.shinpo.core.application.exception.AlreadyExistsApplicationException;
import dev.murasakin.shinpo.core.application.exception.NotFoundApplicationException;
import dev.murasakin.shinpo.core.application.model.user.UserCreateModel;
import dev.murasakin.shinpo.core.application.model.user.UserReadModel;
import dev.murasakin.shinpo.core.application.model.user.UserUpdateModel;
import jakarta.validation.Valid;

import java.util.List;

public interface UserPort {
    List<UserReadModel> listUsers();
    UserReadModel createUser(@Valid UserCreateModel model) throws AlreadyExistsApplicationException;
    UserReadModel getUser(String username) throws NotFoundApplicationException;
    UserReadModel updateUser(String username, UserUpdateModel model) throws NotFoundApplicationException;
    void deleteUser(String username);
}
