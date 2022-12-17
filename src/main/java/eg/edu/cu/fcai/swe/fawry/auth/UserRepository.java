package eg.edu.cu.fcai.swe.fawry.auth;

public interface UserRepository {

    User addNewUser(boolean isAdmin, String username, String email, String password);

    void updateWalletBalance(int userID, float funds);

    User getUserForSignIn(String email, String password);

    User getUserForSignUp(String email, String password);

    User getUserByEmail(String email);

    User getUserByUsername(String username);

    float getUserWalletBalance(int currentUserID);
}
