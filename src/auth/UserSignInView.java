package auth;

import core.View;

public class UserSignInView extends View {
    public final UserAuthController userAuthController;

    public UserSignInView(UserAuthController controller) {
        userAuthController = controller;
    }

    public User show() {
        System.out.println("Sign in");
        User user = userAuthController.signIn(
                inputString("Email: "),
                inputString("Password: ")
        );
        if (user == null) System.out.println("User does not exist");
        return user;
    }
}
