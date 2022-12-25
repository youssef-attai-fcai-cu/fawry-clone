package eg.edu.cu.fcai.swe.fawry.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/admin")
public class AdminAuthController extends AuthController {
    @Autowired
    public AdminAuthController(InMemoryUserRepository _userRepository) {
        super(_userRepository);
    }

    @Override
    User getUser(UserSignInForm form) {
        return userRepository.getAdminByEmailAndPassword(form.email(), form.password());
    }

    @Override
    User createUser(UserSignUpForm form) {
        return userRepository.createAdmin(form.username(), form.email(), form.password());
    }

    @PostMapping("/signup")
    public SignUpResponse adminSignUp(@RequestBody UserSignUpForm form) {
        return signUp(form);
    }

    @PostMapping("/signin")
    public SignInResponse adminSignIn(@RequestBody UserSignInForm form) {
        return signIn(form);
    }
}
