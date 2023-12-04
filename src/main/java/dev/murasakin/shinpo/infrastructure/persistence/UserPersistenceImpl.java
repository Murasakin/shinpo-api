package dev.murasakin.shinpo.infrastructure.persistence;

import dev.murasakin.shinpo.core.application.model.user.UserCreateModel;
import dev.murasakin.shinpo.core.application.model.user.UserReadModel;
import dev.murasakin.shinpo.core.application.model.user.UserUpdateModel;
import dev.murasakin.shinpo.core.application.persistence.UserPersistence;
import dev.murasakin.shinpo.infrastructure.repository.UserRepository;
import dev.murasakin.shinpo.infrastructure.table.UserTable;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

@Service
public class UserPersistenceImpl implements UserPersistence {
    @Autowired
    private UserRepository repository;

    @Override
    public void saveUser(UserCreateModel user) {
        UserTable userTable = new UserTable();
        String[] nameArray = user.name().split(" ", 2);
        userTable.setFirstName(nameArray[0]);
        userTable.setLastName(nameArray.length > 1 ? nameArray[1] : "");
        userTable.setEmailAddress(user.email());
        userTable.setUsername(user.username());
        userTable.setHashedPassword(protectUserPasswordForStorage(user.password()));

        repository.save(userTable);
    }

    @Override
    public List<UserReadModel> retrieveAllUsers() {
        List<UserTable> list = repository.findAll();

        return list
                .stream()
                .map(p -> new UserReadModel(
                            p.getUsername(),
                            String.format("%s %s", p.getFirstName(), p.getLastName()),
                            p.getEmailAddress()
                        )
                )
                .toList();
    }

    @Override
    public @Nullable UserReadModel retrieveByUsername(String username) {
        UserTable userTable = repository.findByUsername(username);
        if (userTable == null) {
            return null;
        }

        return new UserReadModel(
                userTable.getUsername(),
                String.format("%s %s", userTable.getFirstName(), userTable.getLastName()),
                userTable.getEmailAddress()
        );
    }

    @Override
    public void updateUser(String username, UserUpdateModel user) {
        UserTable userToUpdate = repository.findByUsername(username);
        userToUpdate.setFirstName(user.name().split(" ")[0]);
        userToUpdate.setLastName(user.name().split(" ", 2)[1]);
        userToUpdate.setEmailAddress(user.email());

        repository.save(userToUpdate);
    }

    @Override
    public void deleteUser(String username) {
        repository.deleteByUsername(username);
    }

    @Override
    public boolean usernameAlreadyExists(String username) {
        return repository.findByUsername(username) != null;
    }

    private String protectUserPasswordForStorage(String openPassword) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(salt);

        byte[] hashedPassword = md.digest(openPassword.getBytes(StandardCharsets.UTF_8));
        return new String(hashedPassword, StandardCharsets.UTF_8);
    }
}
