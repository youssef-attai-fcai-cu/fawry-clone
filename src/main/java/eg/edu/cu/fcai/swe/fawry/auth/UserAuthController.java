package eg.edu.cu.fcai.swe.fawry.auth;

import eg.edu.cu.fcai.swe.fawry.common.IncorrectUserException;
import eg.edu.cu.fcai.swe.fawry.common.MissingFieldException;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.wallet.InMemoryWalletRepository;
import eg.edu.cu.fcai.swe.fawry.wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public UserAuthController(InMemoryUserRepository _userRepository, InMemoryWalletRepository _walletRepository) {
        userRepository = _userRepository;
        walletRepository = _walletRepository;
    }

    @PostMapping("/signup")
    public Map<String, String> signUp(@RequestBody UserSignUpForm form) {
        if (form.isAdmin() == null)
            throw new MissingFieldException("isAdmin");
        if (Validator.fieldDoesNotExist(form.username()))
            throw new MissingFieldException("username");
        if (Validator.fieldDoesNotExist(form.email()))
            throw new MissingFieldException("email");
        if (Validator.fieldDoesNotExist(form.password()))
            throw new MissingFieldException("password");

        if (!checkConflicts(form.email(), form.username()))
            throw new UserAlreadyExistsException();

        String userId = userRepository.create(form.isAdmin(), form.username(), form.email(), form.password()).id();
        walletRepository.createWallet(userId);
        return Map.of("userId", userId);
    }

    @PostMapping("/signin")
    public Map<String, String> signIn(@RequestBody UserSignInForm form) {
        if (Validator.fieldDoesNotExist(form.email()))
            throw new MissingFieldException("email");

        if (Validator.fieldDoesNotExist(form.password()))
            throw new MissingFieldException("password");

        User user = userRepository.getEmailAndPassword(form.email(), form.password());

        if (user == null)
            throw new IncorrectUserException();

        return Map.of(
                "userId", user.id(),
                "username", user.username(),
                "email", user.email()
        );
    }

    private boolean checkConflicts(String email, String username) {
        User existingUser;

        existingUser = userRepository.getByEmail(email);
        if (existingUser != null) return false;

        existingUser = userRepository.getByUsername(username);
        return existingUser == null;
    }

}
