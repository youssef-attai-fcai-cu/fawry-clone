package auth;

import auth.User;

public interface UserRepository {

    void addNewUser(boolean isAdmin, String username, String email, String password);

    User getUserForSignIn(String email, String password);

    User getUserForSignUp(String email, String password);

    User getUserByEmail(String email);

    User getUserByUsername(String username);
}
