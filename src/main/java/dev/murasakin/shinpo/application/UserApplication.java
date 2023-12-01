package dev.murasakin.shinpo.application;

import dev.murasakin.shinpo.application.exception.NotFoundApplicationException;
import dev.murasakin.shinpo.application.model.UserModel;
import dev.murasakin.shinpo.application.port.UserPort;
import dev.murasakin.shinpo.domain.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Component
public class UserApplication implements UserPort {
    private final Logger LOGGER = Logger.getLogger(UserApplication.class.getName());
    private final List<User> USERS_MOCK = Arrays.stream(new User[] {
            new User("gustavo", "Gustavo F. Balbino", "gustavo@mail.com", "pass"),
            new User("larinea", "Lara Luisa N. Gaspar", "lara@mailmax.com", "pass"),
            new User("vinivec", "Vinicius B. Vechiato", "vinicin@gmail.com", "pass")
    }).toList();
    private final String USER_NOT_FOUND_MESSAGE = "User with username '%s' was not found!";

    @Override
    public List<UserModel> listUsers() {
        LOGGER.info("Listing all users");
        return USERS_MOCK
                .stream()
                .map(p -> new UserModel(p.getUsername(), p.getName(), p.getEmail()))
                .toList();
    }

    @Override
    public UserModel createUser(UserModel model) {
        LOGGER.info("creating a new user");
        return model;
    }

    @Override
    public UserModel getUser(String username) {
        LOGGER.info("getting a user");
        User user = USERS_MOCK
                .stream()
                .filter(p -> p.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new NotFoundApplicationException(String
                        .format(USER_NOT_FOUND_MESSAGE, username)));
        return UserModel.mapEntity(user);
    }
}
