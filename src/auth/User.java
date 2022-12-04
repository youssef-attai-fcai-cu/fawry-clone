package auth;

public record User(boolean isAdmin, String username, String email, String password, int id) {
}
