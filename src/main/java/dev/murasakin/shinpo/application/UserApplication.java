package dev.murasakin.shinpo.application;

import dev.murasakin.shinpo.application.model.UserOutputModel;
import dev.murasakin.shinpo.application.port.UserInputPort;
import dev.murasakin.shinpo.domain.User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Component
public class UserApplication implements UserInputPort {
    private final Logger logger = Logger.getLogger(UserApplication.class.getName());
    private final List<User> usersMock = Arrays.stream(new User[] {
            new User("gustavo", "Gustavo F. Balbino", "gustavo@mail.com", "pass"),
            new User("larinea", "Lara Luisa N. Gaspar", "lara@mailmax.com", "pass"),
            new User("vinivec", "Vinicius B. Vechiato", "vinicin@gmail.com", "pass")
    }).toList();

    @Override
    public List<UserOutputModel> listUsers() {
        logger.info("Listing all users");
        return usersMock
                .stream()
                .map(p -> new UserOutputModel(p.getUsername(), p.getName(), p.getEmail()))
                .toList();
    }
}
