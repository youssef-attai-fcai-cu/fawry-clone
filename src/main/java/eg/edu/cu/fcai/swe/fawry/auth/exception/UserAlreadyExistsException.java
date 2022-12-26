package eg.edu.cu.fcai.swe.fawry.auth.exception;

import eg.edu.cu.fcai.swe.fawry.common.exception.APIException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends APIException {
    public UserAlreadyExistsException() {
        super("A user with this username or email already exists.", HttpStatus.CONFLICT);
    }
}
