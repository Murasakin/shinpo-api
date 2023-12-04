package dev.murasakin.shinpo.core.domain;

import dev.murasakin.shinpo.core.application.model.user.UserCreateModel;
import dev.murasakin.shinpo.core.domain.exception.DomainException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserDomain {
    @Value("${application-values.regex.email}")
    private String emailRegex;
    @Value("${application-values.regex.username}")
    private String usernameRegex;
    @Value("${application-values.regex.password}")
    private String passwordRegex;

    /**
     * Runs domain validations for creating a user in the system.
     * @param model request model to be evaluated.
     * @throws DomainException if any validation fails.
     */
    public void validateCreateUser(UserCreateModel model) throws DomainException {
        if (!validateUsername(model.username())) {
            throw new DomainException("Invalid username: cannot be empty and must contain only letters, " +
                    "numbers and underscores.");
        }
        if (!validateName(model.name())) {
            throw new DomainException("Invalid name: cannot be empty.");
        }
        if (!validatePassword(model.password())) {
            throw new DomainException("Invalid password: password must have a minimum length of 15 characters, " +
                    "and contain at least one number and one special character (@, #, $, %, ^, &, +, =).");
        }
        if (!validateEmail(model.email())) {
            throw new DomainException("The user email is either empty or invalid!");
        }
    }

    private boolean validateUsername(String username) {
        return username != null && username.matches(usernameRegex);
    }

    private boolean validateEmail(String email) {
        return email != null && email.matches(emailRegex);
    }

    private boolean validatePassword(String password) {
        return password != null && password.matches(passwordRegex);
    }

    private boolean validateName(String name) {
        return name != null && !name.isEmpty();
    }
}
