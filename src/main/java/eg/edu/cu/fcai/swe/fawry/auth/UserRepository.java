package eg.edu.cu.fcai.swe.fawry.auth;

public interface UserRepository {

    User createUser(String username, String email, String password);

    User createAdmin(String username, String email, String password);

    User getUserByEmailAndPassword(String email, String password);

    User getAdminByEmailAndPassword(String email, String password);

    User getUserByEmail(String email);

    User getAdminByEmail(String email);

    User getUserByUsername(String username);

    User getAdminByUsername(String username);

    User getUserByToken(String token);

    User getAdminByToken(String token);
}
