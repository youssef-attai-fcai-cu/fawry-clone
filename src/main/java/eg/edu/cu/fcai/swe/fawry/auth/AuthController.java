package eg.edu.cu.fcai.swe.fawry.auth;

import eg.edu.cu.fcai.swe.fawry.common.IncorrectUserException;
import eg.edu.cu.fcai.swe.fawry.common.Validator;

public abstract class AuthController {
    protected final UserRepository userRepository;

    public AuthController(InMemoryUserRepository _userRepository) {
        userRepository = _userRepository;
    }

    abstract User getUser(UserSignInForm form);

    abstract User createUser(UserSignUpForm form);

    protected SignUpResponse signUp(UserSignUpForm form) {
        Validator.assertFieldExists("username", form.username());
        Validator.assertFieldExists("email", form.email());
        Validator.assertFieldExists("password", form.password());

        boolean userExists = userRepository.getUserByEmail(form.email()) != null ||
                userRepository.getAdminByEmail(form.email()) != null ||
                userRepository.getUserByUsername(form.username()) != null ||
                userRepository.getAdminByUsername(form.username()) != null;

        if (userExists) throw new UserAlreadyExistsException();

        User newUser = createUser(form);
        return new SignUpResponse(newUser.token());
    }

    protected SignInResponse signIn(UserSignInForm form) {
        Validator.assertFieldExists("email", form.email());
        Validator.assertFieldExists("password", form.password());
        User user = getUser(form);
        if (user == null) throw new IncorrectUserException();
        return new SignInResponse(user.username(), user.token());
    }
}
