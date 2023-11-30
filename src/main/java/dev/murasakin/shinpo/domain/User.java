package dev.murasakin.shinpo.domain;

import java.util.UUID;

public class User {
    private final String username;
    private final String name;
    private final String email;
    private final String securedPassword;

    public User(String username, String name, String email, String securedPassword) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.securedPassword = securedPassword;
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

    public String getSecuredPassword() {
        return securedPassword;
    }
}
