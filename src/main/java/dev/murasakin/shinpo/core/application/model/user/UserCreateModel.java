package dev.murasakin.shinpo.core.application.model.user;

import jakarta.validation.constraints.Pattern;

import java.util.Objects;

public final class UserCreateModel {
    private final String username;
    @Pattern(regexp = "^[a-zA-Z0-9._]{5,}$")
    private final String name;
    private final String email;
    private final String password;

    public UserCreateModel(String username, String name, String email, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UserCreateModel) obj;
        return Objects.equals(this.username, that.username) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, name, email, password);
    }

    @Override
    public String toString() {
        return "UserCreateModel[" +
                "username=" + username + ", " +
                "name=" + name + ", " +
                "email=" + email + ", " +
                "password=" + password + ']';
    }

}
