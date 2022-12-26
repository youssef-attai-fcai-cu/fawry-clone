package eg.edu.cu.fcai.swe.fawry.payment.exception;

import eg.edu.cu.fcai.swe.fawry.common.exception.APIException;
import org.springframework.http.HttpStatus;

public class InvalidAmount extends APIException {
    public InvalidAmount(String what, Number amount) {
        super("Invalid amount of " + what + ": " + amount, HttpStatus.BAD_REQUEST);
    }
}
