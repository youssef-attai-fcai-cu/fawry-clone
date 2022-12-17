package eg.edu.cu.fcai.swe.fawry.payment;

import eg.edu.cu.fcai.swe.fawry.common.APIException;
import org.springframework.http.HttpStatus;

public class InvalidServiceProvider extends APIException {
    public InvalidServiceProvider(String providerId) {
        super("Service provider with ID " + providerId + " does not exist", HttpStatus.NOT_FOUND);
    }
}
