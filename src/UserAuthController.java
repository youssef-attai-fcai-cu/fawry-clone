public class UserAuthController {
    private final UserRepository userRepository;
    private User currentUser;
    private boolean isAdmin;

    public UserAuthController(UserRepository repository) {
        userRepository = repository;
    }

    public void signUp(String username, String email, String password, String confirmPassword) {
        if (username.isEmpty()) return;
        if (email.isEmpty()) return;
        if (password.isEmpty()) return;
        if (!password.equals(confirmPassword)) return;
        if (!checkConflicts(email, username)) return;

        userRepository.addNewUser(new User(isAdmin, username, email, password));
        signIn(email, password);
    }

    public void signIn(String email, String password) {
        if (email.isEmpty()) return;
        if (password.isEmpty()) return;

        User user = userRepository.getUserForSignIn(email, password);
        if (user == null) return;

        currentUser = user;
    }

    void signOut() {
        currentUser = null;
    }

    public boolean checkConflicts(String email, String username) {
        User existingUser;

        existingUser = userRepository.getUserByEmail(email);
        if (existingUser != null) return false;

        existingUser = userRepository.getUserByUsername(username);
        return existingUser == null;
    }

    public void toggleAdmin() {
        isAdmin = !isAdmin;
    }
}
