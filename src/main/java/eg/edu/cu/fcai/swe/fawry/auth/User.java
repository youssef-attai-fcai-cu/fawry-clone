package eg.edu.cu.fcai.swe.fawry.auth;


public record User
        (Boolean isAdmin,
         String username,
         String email,
         String password,
         Integer id,
         Float walletBalance) {
}
