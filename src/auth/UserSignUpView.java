package auth;

import ui_utils.ButtonView;

import java.util.List;
import java.util.Scanner;

public class UserSignUpView {
    private final UserAuthController controller;
    private final Scanner scanner = new Scanner(System.in);
    private String username = "";
    private String userEmail = "";
    private String userPassword = "";
    private String confirmPassword = "";
    private boolean isAdmin = false;

    public UserSignUpView(UserAuthController controller) {
        this.controller = controller;
    }

    public void inputUsername() {
        do {
            System.out.print("Username: ");
            this.username = this.scanner.nextLine();
        } while (this.username.equals(""));
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

    public void inputConfirmPassword() {
        do {
            System.out.print("Confirm password: ");
            this.confirmPassword = this.scanner.nextLine();
        } while (this.confirmPassword.equals(""));
    }

    private void inputIsAdmin() {
        String yn = "";
        do {
            System.out.print("Admin?  (y/n) ");
            yn = this.scanner.nextLine().toLowerCase();
        } while (!yn.equals("y") && !yn.equals("n"));
        this.isAdmin = yn.equalsIgnoreCase("y");
    }

    public User show() {
        System.out.println("Sign up\n");

        this.inputIsAdmin();

        this.inputUsername();

        this.inputUserEmail();

        this.inputUserPassword();

        this.inputConfirmPassword();


        return this.controller.signUp(isAdmin, username, userEmail, userPassword, confirmPassword);
    }
}
