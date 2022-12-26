package eg.edu.cu.fcai.swe.fawry.common.exception;

public class APIError {
    private String message;

    public APIError(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}