package eg.edu.cu.fcai.swe.fawry.refund.exception;

import eg.edu.cu.fcai.swe.fawry.common.exception.APIException;
import org.springframework.http.HttpStatus;

public class RefundAlreadyRequested extends APIException {
    public RefundAlreadyRequested(String transactionId) {
        super("A refund has already been requested for transaction with ID " + transactionId, HttpStatus.CONFLICT);
    }
}
