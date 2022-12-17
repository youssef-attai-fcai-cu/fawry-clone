package eg.edu.cu.fcai.swe.fawry.common;

import org.springframework.http.HttpStatus;

public class MissingFieldException extends APIException {
    public MissingFieldException(String field) {
        super("Missing field: " + field, HttpStatus.BAD_REQUEST);
    }
}
