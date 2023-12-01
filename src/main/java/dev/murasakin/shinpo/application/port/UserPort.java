package dev.murasakin.shinpo.application.port;

import dev.murasakin.shinpo.application.model.UserModel;

import java.util.List;

public interface UserPort {
    List<UserModel> listUsers();
    UserModel createUser(UserModel model);
    UserModel getUser(String username);
}
