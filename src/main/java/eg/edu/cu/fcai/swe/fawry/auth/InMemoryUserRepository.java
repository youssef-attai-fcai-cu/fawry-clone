package eg.edu.cu.fcai.swe.fawry.auth;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InMemoryUserRepository implements UserRepository {
    //List for Users
    private final List<User> users = new ArrayList<>();

    // Adding new users in List
    @Override
    public User create(boolean isAdmin, String username, String email, String password) {
        User newUser = new User(isAdmin, username, email, password, "USER-" + UUID.randomUUID());
        users.add(newUser);
        return newUser;
    }

    @Override
    public User getEmailAndPassword(String email, String password) {
        return users.stream().filter(user -> user.email().equals(email) && user.password().equals(password)).findFirst().orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return users.stream().filter(user -> user.email().equals(email)).findFirst().orElse(null);
    }

    @Override
    public User getByUsername(String username) {
        return users.stream().filter(user -> user.username().equals(username)).findFirst().orElse(null);
    }

    @Override
    public User getById(String userId) {
        return users.stream().filter(user -> user.id().equals(userId)).findFirst().orElse(null);
    }
}
