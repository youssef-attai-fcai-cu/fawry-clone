package eg.edu.cu.fcai.swe.fawry.auth.model;


public record User
        (String userId,
         String username,
         String email,
         String password,
         String token) {
}
