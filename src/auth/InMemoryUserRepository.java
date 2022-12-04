package auth;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {
    //List for Users
    private static final List<User> users = new ArrayList<>();

    // Adding new users in List
    @Override
    public void addNewUser(boolean isAdmin, String username, String email, String password) {
        users.add(new User(isAdmin, username, email, password, users.size()));
    }

    @Override
    public User getUserForSignIn(String email, String password) {
        for (User user : users)
            if (user.email().equals(email)) {
                if (user.password().equals(password))
                    return user;
                return null;
            }
        return null;
    }

    @Override
    public User getUserForSignUp(String email, String password) {
        for (User user : users)
            if (user.email().equals(email)) {
                if (user.password().equals(password))
                    return user;
                return null;
            }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : users)
            if (user.email().equals(email))
                return user;
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        for (User user : users)
            if (user.username().equals(username))
                return user;
        return null;


    }
}
