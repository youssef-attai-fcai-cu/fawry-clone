import ui_utils.ButtonView;

public class UserSignUpView {
    private final ButtonView signUpButton = new ButtonView();
    private final ButtonView isAdminCheckbox = new ButtonView();
    private String username = "";
    private String userEmail = "";
    private String userPassword = "";
    private String confirmPassword = "";

    public UserSignUpView(UserAuthController controller) {
        this.isAdminCheckbox.setClickListener(controller::toggleAdmin);
        this.signUpButton.setClickListener(() -> {
            controller.signUp(username, userEmail, userPassword, confirmPassword);
        });
    }

    String getUsername() {
        return this.username;
    }

    String getUserEmail() {
        return this.userEmail;
    }

    String getUserPassword() {
        return this.userPassword;
    }

    String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void inputUsername(String username) {
        this.username = username;
    }

    public void inputUserEmail(String email) {
        this.userEmail = email;
    }

    public void inputUserPassword(String password) {
        this.userPassword = password;
    }

    public void inputConfirmPassword(String password) {
        this.confirmPassword = password;
    }

    public void clickSignUp() {
        this.signUpButton.click();
    }

    public void toggleAdminCheckbox() {
        this.isAdminCheckbox.click();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
