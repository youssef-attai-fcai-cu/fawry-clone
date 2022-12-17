package eg.edu.cu.fcai.swe.fawry.common;

import org.springframework.http.HttpStatus;

public class MissingFieldException extends APIException {
    public MissingFieldException(String message) {
        super("Missing field: " + message, HttpStatus.BAD_REQUEST);
    }
}
