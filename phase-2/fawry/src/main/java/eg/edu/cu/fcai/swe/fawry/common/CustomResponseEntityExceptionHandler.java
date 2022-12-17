package eg.edu.cu.fcai.swe.fawry.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(APIException.class)
    public final ResponseEntity<APIError> handleAPIException(APIException exception, WebRequest request) {
        return new ResponseEntity<>(new APIError(exception.getMessage()), exception.getStatusCode());
    }
}