package eg.edu.cu.fcai.swe.fawry.auth.controller;

import eg.edu.cu.fcai.swe.fawry.auth.model.User;
import eg.edu.cu.fcai.swe.fawry.auth.repository.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.auth.request.UserSignInRequestBody;
import eg.edu.cu.fcai.swe.fawry.auth.request.UserSignUpRequestBody;
import eg.edu.cu.fcai.swe.fawry.auth.response.SignInResponse;
import eg.edu.cu.fcai.swe.fawry.auth.response.SignUpResponse;
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
    User getUser(UserSignInRequestBody body) {
        return userRepository.getAdminByEmailAndPassword(body.email(), body.password());
    }

    @Override
    User createUser(UserSignUpRequestBody body) {
        return userRepository.createAdmin(body.username(), body.email(), body.password());
    }

    @PostMapping("/signup")
    public SignUpResponse adminSignUp(@RequestBody UserSignUpRequestBody body) {
        return signUp(body);
    }

    @PostMapping("/signin")
    public SignInResponse adminSignIn(@RequestBody UserSignInRequestBody body) {
        return signIn(body);
    }
}
