package eg.edu.cu.fcai.swe.fawry.auth;


public record User
        (String userId, Boolean isAdmin, String username, String email, String password) {
}
