package eg.edu.cu.fcai.swe.fawry.auth;

import eg.edu.cu.fcai.swe.fawry.common.IncorrectUserException;
import eg.edu.cu.fcai.swe.fawry.common.MissingFieldException;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private final InMemoryUserRepository userRepository;

    @Autowired
    public UserAuthController(InMemoryUserRepository repository) {
        userRepository = repository;
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody UserSignUpForm form) {
        if (Validator.fieldDoesNotExist(form.username()))
            throw new MissingFieldException("username");
        if (Validator.fieldDoesNotExist(form.email()))
            throw new MissingFieldException("email");
        if (Validator.fieldDoesNotExist(form.password()))
            throw new MissingFieldException("password");

        if (!checkConflicts(form.email(), form.username()))
            throw new UserAlreadyExistsException();

        return userRepository.addNewUser(form.isAdmin(), form.username(), form.email(), form.password());
    }

    @PostMapping("/signin")
    public User signIn(@RequestBody UserSignInForm form) {
        if (Validator.fieldDoesNotExist(form.email()))
            throw new MissingFieldException("email");

        if (Validator.fieldDoesNotExist(form.password()))
            throw new MissingFieldException("password");

        User user = userRepository.getUserForSignIn(form.email(), form.password());

        if (user == null)
            throw new IncorrectUserException();

        return user;
    }

    private boolean checkConflicts(String email, String username) {
        User existingUser;

        existingUser = userRepository.getUserByEmail(email);
        if (existingUser != null) return false;

        existingUser = userRepository.getUserByUsername(username);
        return existingUser == null;
    }

}
