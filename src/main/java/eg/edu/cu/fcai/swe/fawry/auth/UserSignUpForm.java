package eg.edu.cu.fcai.swe.fawry.auth;

public record UserSignUpForm(
        String username,
        String email,
        String password
) {
}
