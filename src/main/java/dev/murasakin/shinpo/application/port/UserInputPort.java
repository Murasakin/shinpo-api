package dev.murasakin.shinpo.application.port;

import dev.murasakin.shinpo.application.model.UserOutputModel;

import java.util.List;

public interface UserInputPort {
    List<UserOutputModel> listUsers();
}
