package eg.edu.cu.fcai.swe.fawry.payment;

import eg.edu.cu.fcai.swe.fawry.common.APIException;
import org.springframework.http.HttpStatus;

public class PaymentException extends APIException {
    public PaymentException() {
        super("Something went wrong, unsuccessful payment.", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
