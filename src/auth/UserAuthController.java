package auth;

public class UserAuthController {
    private final UserRepository userRepository;

    public UserAuthController(UserRepository repository) {
        userRepository = repository;
    }

    public User signUp(boolean isAdmin, String username, String email, String password, String confirmPassword) {
        if (username.isEmpty()) return null;
        if (email.isEmpty()) return null;
        if (password.isEmpty()) return null;
        if (!password.equals(confirmPassword)) return null;
        if (!checkConflicts(email, username)) return null;

        userRepository.addNewUser(isAdmin, username, email, password);
        return signIn(email, password);
    }

    public User signIn(String email, String password) {
        if (email.isEmpty()) return null;
        if (password.isEmpty()) return null;

        return userRepository.getUserForSignIn(email, password);
    }

    public boolean checkConflicts(String email, String username) {
        User existingUser;

        existingUser = userRepository.getUserByEmail(email);
        if (existingUser != null) return false;

        existingUser = userRepository.getUserByUsername(username);
        return existingUser == null;
    }

}
