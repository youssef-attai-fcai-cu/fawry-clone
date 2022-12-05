package auth;

import core.View;

public class UserAuthView extends View {
    public final UserAuthController userAuthController;
    private final UserSignInView userSignInView;
    private final UserSignUpView userSignUpView;

    public UserAuthView(UserAuthController controller) {
        userAuthController = controller;
        userSignInView = new UserSignInView(userAuthController);
        userSignUpView = new UserSignUpView(userAuthController);
    }

    public User show() {
        System.out.println("Fawry");
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        int choice = inputWithinRange("> ", 1, 2);
        return switch (choice) {
            case 1 -> userSignInView.show();
            case 2 -> userSignUpView.show();
            default -> null;
        };
    }
}
