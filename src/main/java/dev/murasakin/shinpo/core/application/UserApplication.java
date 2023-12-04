package dev.murasakin.shinpo.core.application;

import dev.murasakin.shinpo.core.application.exception.AlreadyExistsApplicationException;
import dev.murasakin.shinpo.core.application.exception.NotFoundApplicationException;
import dev.murasakin.shinpo.core.application.model.user.UserCreateModel;
import dev.murasakin.shinpo.core.application.model.user.UserUpdateModel;
import dev.murasakin.shinpo.core.application.persistence.UserPersistence;
import dev.murasakin.shinpo.core.application.model.user.UserReadModel;
import dev.murasakin.shinpo.core.application.port.UserPort;
import dev.murasakin.shinpo.core.domain.UserDomain;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.logging.Logger;

@Component
@Validated
public class UserApplication implements UserPort {
    private final Logger LOGGER = Logger.getLogger(UserApplication.class.getName());
    private final String USER_NOT_FOUND_MESSAGE = "User with username '%s' was not found!";
    @Autowired
    private UserPersistence userPersistence;
    private final UserDomain userDomain;

    public UserApplication(UserDomain domain) {
        this.userDomain = domain;
    }

    @Override
    public List<UserReadModel> listUsers() {
        LOGGER.info("Listing all users");

        return userPersistence.retrieveAllUsers();
    }

    @Override
    public UserReadModel createUser(@Valid UserCreateModel model) {
        LOGGER.info("creating a new user");
        final String USER_ALREADY_EXISTS_MESSAGE = "A user with username %s already exists!";

        if (userPersistence.usernameAlreadyExists(model.username())) {
            throw new AlreadyExistsApplicationException(
                    String.format(USER_ALREADY_EXISTS_MESSAGE, model.username()));
        }
        userDomain.validateCreateUser(model);
        userPersistence.saveUser(model);

        return new UserReadModel(model.username(), model.name(), model.email());
    }

    @Override
    public UserReadModel getUser(String username) {
        LOGGER.info("getting a user");

        UserReadModel user = userPersistence.retrieveByUsername(username);
        if (user == null) {
            throw new NotFoundApplicationException(String.format(USER_NOT_FOUND_MESSAGE, username));
        }
        return user;
    }

    @Override
    public UserReadModel updateUser(String username, UserUpdateModel model) {
        if (!userPersistence.usernameAlreadyExists(username)) {
            throw new NotFoundApplicationException(String.format(USER_NOT_FOUND_MESSAGE, username));
        }

        userPersistence.updateUser(username, model);

        return new UserReadModel(username, model.name(), model.email());
    }

    @Override
    public void deleteUser(String username) {
        userPersistence.deleteUser(username);
    }
}
