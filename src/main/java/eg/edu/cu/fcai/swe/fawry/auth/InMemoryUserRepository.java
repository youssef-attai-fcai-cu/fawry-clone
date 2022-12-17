package eg.edu.cu.fcai.swe.fawry.auth;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryUserRepository implements UserRepository {
    //List for Users
    private final List<User> users = new ArrayList<>();

    // Adding new users in List
    @Override
    public User addNewUser(boolean isAdmin, String username, String email, String password) {
        User newUser = new User(isAdmin, username, email, password, users.size(), 0.0f);
        users.add(newUser);
        return newUser;
    }

    @Override
    public void updateWalletBalance(int userID, float funds) {
        List<User> userList = this.users;
        for (int i = 0; i < userList.size(); i++) {
            User u = userList.get(i);
            if (u.id() == userID) {
                users.set(i, new User(
                        u.isAdmin(),
                        u.username(),
                        u.email(),
                        u.password(),
                        u.id(),
                        u.walletBalance() + funds));
                break;
            }
        }
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

    @Override
    public float getUserWalletBalance(int userID) {
        for (User user : this.users) {
            if (user.id() == userID) {
                return user.walletBalance();
            }
        }
        return -1;
    }
}
