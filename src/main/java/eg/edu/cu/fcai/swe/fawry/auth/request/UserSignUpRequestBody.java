package eg.edu.cu.fcai.swe.fawry.auth.request;

public record UserSignUpRequestBody(
        String username,
        String email,
        String password
) {
}
