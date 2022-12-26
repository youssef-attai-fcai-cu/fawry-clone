package eg.edu.cu.fcai.swe.fawry.payment.exception;

import eg.edu.cu.fcai.swe.fawry.common.exception.APIException;
import org.springframework.http.HttpStatus;

public class PaymentException extends APIException {
    public PaymentException() {
        super("Something went wrong, unsuccessful payment.", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
