import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements UserRepository {
    //List for Users
    private static final List<User> users = new ArrayList<>();

    // Adding new users in List
    @Override
    public void addNewUser(User newUser) {
        users.add(newUser);
    }
    @Override
    public User getUserForSignIn(String email, String password) {
        for (User user : users)
            if (user.getEmail().equals(email)) {
                if (user.checkpasswword(password))
                    return user;
                return null;
            }
        return null;
    }
    @Override
    public User getUserForSignUp(String email, String password) {
        for (User user : users)
            if (user.getEmail().equals(email)) {
                if (user.checkpasswword(password))
                    return user;
                return null;
            }
        return null;
    }
    @Override
    public User getUserByEmail(String email) {
        for (User user : users)
            if (user.getEmail().equals(email))
                return user;
        return null;
    }
    @Override
    public User getUserByUsername(String username) {
        for (User user : users)
            if (user.getUsername().equals(username))
                return user;
        return null;


    }
}
