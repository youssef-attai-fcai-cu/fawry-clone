package eg.edu.cu.fcai.swe.fawry.common;

import org.springframework.http.HttpStatus;

public class IncorrectUserException extends APIException {
    public IncorrectUserException() {
        super("Incorrect email or password.", HttpStatus.BAD_REQUEST);
    }
}
