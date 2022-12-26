package eg.edu.cu.fcai.swe.fawry.auth.response;

public record SignInResponse(
        String username,
        String token
) {
}
