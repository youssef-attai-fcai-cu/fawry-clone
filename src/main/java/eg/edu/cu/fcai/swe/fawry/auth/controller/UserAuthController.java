package eg.edu.cu.fcai.swe.fawry.auth.controller;

import eg.edu.cu.fcai.swe.fawry.auth.model.User;
import eg.edu.cu.fcai.swe.fawry.auth.repository.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.auth.request.UserSignInRequestBody;
import eg.edu.cu.fcai.swe.fawry.auth.request.UserSignUpRequestBody;
import eg.edu.cu.fcai.swe.fawry.auth.response.SignInResponse;
import eg.edu.cu.fcai.swe.fawry.auth.response.SignUpResponse;
import eg.edu.cu.fcai.swe.fawry.wallet.repository.InMemoryWalletRepository;
import eg.edu.cu.fcai.swe.fawry.common.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
public class UserAuthController extends AuthController {
    private final WalletRepository walletRepository;

    @Autowired
    public UserAuthController(InMemoryUserRepository _userRepository, InMemoryWalletRepository _walletRepository) {
        super(_userRepository);
        walletRepository = _walletRepository;
    }

    @Override
    User createUser(UserSignUpRequestBody form) {
        User newUser = userRepository.createUser(form.username(), form.email(), form.password());
        walletRepository.createWallet(newUser.userId());
        return newUser;
    }

    @Override
    User getUser(UserSignInRequestBody form) {
        return userRepository.getUserByEmailAndPassword(form.email(), form.password());
    }

    @PostMapping("/signup")
    public SignUpResponse userSignUp(@RequestBody UserSignUpRequestBody form) {
        return signUp(form);
    }

    @PostMapping("/signin")
    public SignInResponse userSignIn(@RequestBody UserSignInRequestBody form) {
        return signIn(form);
    }
}
