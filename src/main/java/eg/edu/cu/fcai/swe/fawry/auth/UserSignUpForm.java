package eg.edu.cu.fcai.swe.fawry.auth;

public record UserSignUpForm(
        Boolean isAdmin,
        String username,
        String email,
        String password
) {
}
