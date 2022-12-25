package eg.edu.cu.fcai.swe.fawry.auth;

public record SignInResponse(
        String username,
        String token
) {
}
