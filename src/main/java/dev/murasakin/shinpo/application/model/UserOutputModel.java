package dev.murasakin.shinpo.application.model;

public class UserOutputModel {
    private final String username;
    private final String name;
    private final String email;

    public UserOutputModel(String username, String name, String email) {
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
}
