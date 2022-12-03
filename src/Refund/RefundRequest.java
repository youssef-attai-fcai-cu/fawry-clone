package Refund;

public class RefundRequest {
    private final int requestID;
    private final int transcationID;
    private final int userID;
    private final float Amount;
    private final String serviceType;
    private final RefundStatus refundStatus;


    public RefundRequest(int requestID, int transcationID, int userID, float amount, String serviceType, RefundStatus refundStatus) {
        this.requestID = requestID;
        this.transcationID = transcationID;
        this.userID = userID;
        Amount = amount;
        this.serviceType = serviceType;
        this.refundStatus = refundStatus;
    }
}
