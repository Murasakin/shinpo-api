package dev.murasakin.shinpo.application.model;

import dev.murasakin.shinpo.domain.User;

public class UserModel {
    private final String username;
    private final String name;
    private final String email;

    public UserModel(String username, String name, String email) {
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static UserModel mapEntity(User user) {
        return new UserModel(user.getUsername(), user.getName(), user.getEmail());
    }
}
