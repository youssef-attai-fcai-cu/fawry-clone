package auth;

import ui_utils.ButtonView;
import ui_utils.ClickListener;

import java.util.Scanner;

public class UserSignInView {
    private final UserAuthController controller;
    private String userEmail = "";
    private String userPassword = "";

    private final Scanner scanner = new Scanner(System.in);

    public UserSignInView(UserAuthController controller) {
        this.controller = controller;
    }

    public void inputUserEmail() {
        do {
            System.out.print("Email: ");
            this.userEmail = this.scanner.nextLine();
        } while (this.userEmail.equals(""));
    }

    public void inputUserPassword() {
        do {
            System.out.print("Password: ");
            this.userPassword = this.scanner.nextLine();
        } while (this.userPassword.equals(""));
    }

    public User show() {
        System.out.println("Sign in\n");

        this.inputUserEmail();

        this.inputUserPassword();

        return this.controller.signIn(userEmail, userPassword);
    }
}
