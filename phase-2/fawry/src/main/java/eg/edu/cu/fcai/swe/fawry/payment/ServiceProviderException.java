package eg.edu.cu.fcai.swe.fawry.payment;

import eg.edu.cu.fcai.swe.fawry.common.APIException;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;
import org.springframework.http.HttpStatus;

public class ServiceProviderException extends APIException {
    public ServiceProviderException(ServiceProvider service) {
        super("An error occurred with " + service.getName(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}
