package eg.edu.cu.fcai.swe.fawry.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidToken extends APIException {
    public InvalidToken(String who) {
        super("Invalid " + who + " token.", HttpStatus.BAD_REQUEST);
    }
}
