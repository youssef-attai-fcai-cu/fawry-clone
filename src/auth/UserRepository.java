package auth;

public interface UserRepository {

    void addNewUser(boolean isAdmin, String username, String email, String password);
    void updateWalletBalance(int userID, float funds);

    User getUserForSignIn(String email, String password);

    User getUserForSignUp(String email, String password);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    float getUserWalletBalance(int currentUserID);
}
