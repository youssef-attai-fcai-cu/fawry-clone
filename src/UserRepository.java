public interface UserRepository {

    void addNewUser(User user);

    User getUserForSignIn(String email, String password);

    User getUserForSignUp(String email, String password);

    User getUserByEmail(String email);

    User getUserByUsername(String username);
}
