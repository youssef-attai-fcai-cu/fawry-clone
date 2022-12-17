package eg.edu.cu.fcai.swe.fawry.auth;

public record UserSignUpForm(
        boolean isAdmin,
        String username,
        String email,
        String password
) {
}
