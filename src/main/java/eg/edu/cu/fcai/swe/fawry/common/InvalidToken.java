package eg.edu.cu.fcai.swe.fawry.common;

import org.springframework.http.HttpStatus;

public class InvalidToken extends APIException {
    public InvalidToken() {
        super("Invalid token.", HttpStatus.BAD_REQUEST);
    }
}
