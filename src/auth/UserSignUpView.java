package auth;


import core.View;

public class UserSignUpView extends View {
    public final UserAuthController userAuthController;

    public UserSignUpView(UserAuthController controller) {
        userAuthController = controller;
    }

    public User show() {
        System.out.println("Sign up");
        User user = userAuthController.signUp(
                inputBoolean("Admin? "),
                inputString("Username: "),
                inputString("Email: "),
                inputString("Password: "),
                inputString("Confirm password: "));
        if (user == null) System.out.println("User with this email or username already exists");
        return user;
    }
}
