package eg.edu.cu.fcai.swe.fawry.wallet.exception;

import eg.edu.cu.fcai.swe.fawry.common.exception.APIException;
import org.springframework.http.HttpStatus;

public class InsufficientWalletBalance extends APIException {
    public InsufficientWalletBalance() {
        super("Your wallet balance is not enough to complete this payment transaction", HttpStatus.BAD_REQUEST);
    }
}
