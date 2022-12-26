package eg.edu.cu.fcai.swe.fawry.auth.controller;

import eg.edu.cu.fcai.swe.fawry.auth.exception.UserAlreadyExistsException;
import eg.edu.cu.fcai.swe.fawry.auth.model.User;
import eg.edu.cu.fcai.swe.fawry.auth.repository.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.auth.request.UserSignInRequestBody;
import eg.edu.cu.fcai.swe.fawry.auth.request.UserSignUpRequestBody;
import eg.edu.cu.fcai.swe.fawry.auth.response.SignInResponse;
import eg.edu.cu.fcai.swe.fawry.auth.response.SignUpResponse;
import eg.edu.cu.fcai.swe.fawry.auth.exception.IncorrectUserException;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.common.repository.UserRepository;

public abstract class AuthController {
    protected final UserRepository userRepository;

    public AuthController(InMemoryUserRepository _userRepository) {
        userRepository = _userRepository;
    }

    abstract User getUser(UserSignInRequestBody form);

    abstract User createUser(UserSignUpRequestBody form);

    protected SignUpResponse signUp(UserSignUpRequestBody form) {
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

    protected SignInResponse signIn(UserSignInRequestBody form) {
        Validator.assertFieldExists("email", form.email());
        Validator.assertFieldExists("password", form.password());
        User user = getUser(form);
        if (user == null) throw new IncorrectUserException();
        return new SignInResponse(user.username(), user.token());
    }
}
