import ui_utils.ButtonView;
import ui_utils.ClickListener;

public class UserSignInView {
    private final ButtonView signInButton = new ButtonView();
    private String userEmail = "";
    private String userPassword = "";

    public UserSignInView(UserAuthController controller) {
        this.signInButton.setClickListener(new ClickListener() {
            @Override
            public void handle() {
                controller.signIn(userEmail, userPassword);
            }
        });
    }

    String getUserEmail() {
        return this.userEmail;
    }

    String getUserPassword() {
        return this.userPassword;
    }

    public void inputUserEmail(String email) {
        this.userEmail = email;
    }

    public void inputUserPassword(String password) {
        this.userPassword = password;
    }

    void clickSignIn() {
        this.signInButton.click();
    }
}
