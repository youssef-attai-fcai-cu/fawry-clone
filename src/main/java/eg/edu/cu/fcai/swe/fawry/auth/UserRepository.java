package eg.edu.cu.fcai.swe.fawry.auth;

public interface UserRepository {

    User create(boolean isAdmin, String username, String email, String password);

    User getEmailAndPassword(String email, String password);

    User getByEmail(String email);

    User getByUsername(String username);

    User getById(String userId);
}
