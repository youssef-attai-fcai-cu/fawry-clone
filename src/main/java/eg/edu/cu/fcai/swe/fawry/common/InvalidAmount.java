package eg.edu.cu.fcai.swe.fawry.common;

import eg.edu.cu.fcai.swe.fawry.common.APIException;
import org.springframework.http.HttpStatus;

public class InvalidAmount extends APIException {
    public InvalidAmount(Float amount) {
        super("Invalid amount of funds: " + amount, HttpStatus.BAD_REQUEST);
    }
}
