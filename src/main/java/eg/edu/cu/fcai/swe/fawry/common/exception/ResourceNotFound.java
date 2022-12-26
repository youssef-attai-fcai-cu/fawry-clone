package eg.edu.cu.fcai.swe.fawry.common.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFound extends APIException {
    public ResourceNotFound(String resourceType, String resourceId) {
        super(resourceType + " with ID " + resourceId + " does not exist", HttpStatus.NOT_FOUND);
    }
}
