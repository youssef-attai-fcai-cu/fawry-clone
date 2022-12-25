package eg.edu.cu.fcai.swe.fawry.auth;

import eg.edu.cu.fcai.swe.fawry.wallet.InMemoryWalletRepository;
import eg.edu.cu.fcai.swe.fawry.wallet.WalletRepository;
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
    User createUser(UserSignUpForm form) {
        User newUser = userRepository.createUser(form.username(), form.email(), form.password());
        walletRepository.createWallet(newUser.userId());
        return newUser;
    }

    @Override
    User getUser(UserSignInForm form) {
        return userRepository.getUserByEmailAndPassword(form.email(), form.password());
    }

    @PostMapping("/signup")
    public SignUpResponse userSignUp(@RequestBody UserSignUpForm form) {
        return signUp(form);
    }

    @PostMapping("/signin")
    public SignInResponse userSignIn(@RequestBody UserSignInForm form) {
        return signIn(form);
    }
}
