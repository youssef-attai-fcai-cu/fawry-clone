package eg.edu.cu.fcai.swe.fawry.auth;


public record User
        (String userId,
         String username,
         String email, String password,
         String token) {
}
