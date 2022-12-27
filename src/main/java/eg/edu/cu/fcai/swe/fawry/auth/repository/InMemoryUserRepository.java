package eg.edu.cu.fcai.swe.fawry.auth.repository;

import eg.edu.cu.fcai.swe.fawry.auth.model.User;
import eg.edu.cu.fcai.swe.fawry.common.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryUserRepository implements UserRepository {
    //List for Users
    private final List<User> users = new ArrayList<>();
    private final List<User> admins = new ArrayList<>();

    // Adding new users in List
    @Override
    public User createUser(String username, String email, String password) {
        User newUser = new User(
                InMemoryUserRepositoryUtils.newUserId(),
                username,
                email,
                password,
                InMemoryUserRepositoryUtils.newUserToken()
        );
        users.add(newUser);
        return newUser;
    }

    @Override
    public User createAdmin(String username, String email, String password) {
        User newAdmin = new User(
                InMemoryUserRepositoryUtils.newAdminId(),
                username,
                email,
                password,
                InMemoryUserRepositoryUtils.newUserToken()
        );
        admins.add(newAdmin);
        return newAdmin;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return users.stream().filter(user -> user.email().equals(email) && user.password().equals(password)).findFirst().orElse(null);
    }

    @Override
    public User getAdminByEmailAndPassword(String email, String password) {
        return admins.stream().filter(admin -> admin.email().equals(email) && admin.password().equals(password)).findFirst().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return users.stream().filter(user -> user.email().equals(email)).findFirst().orElse(null);
    }

    @Override
    public User getAdminByEmail(String email) {
        return admins.stream().filter(admin -> admin.email().equals(email)).findFirst().orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return users.stream().filter(user -> user.username().equals(username)).findFirst().orElse(null);
    }

    @Override
    public User getAdminByUsername(String username) {
        return admins.stream().filter(admin -> admin.username().equals(username)).findFirst().orElse(null);
    }

    @Override
    public User getUserByToken(String token) {
        return users.stream().filter(user -> user.token().equals(token)).findFirst().orElse(null);
    }

    @Override
    public User getAdminByToken(String token) {
        return admins.stream().filter(admin -> admin.token().equals(token)).findFirst().orElse(null);
    }

    @Override
    public boolean userExists(String email, String username) {
        return getUserByEmail(email) != null ||
                getAdminByEmail(email) != null ||
                getUserByUsername(username) != null ||
                getAdminByUsername(username) != null;
    }
}
