package refund;

public class RefundRequest {
    public final int requestID;
    public final int transactionID;
    public final int userID;

    public void setRefundStatus(RefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    private RefundStatus refundStatus;


    public RefundRequest(int requestID, int transactionID, int userID, RefundStatus refundStatus) {
        this.requestID = requestID;
        this.transactionID = transactionID;
        this.userID = userID;
        this.refundStatus = refundStatus;
    }

    @Override
    public String toString() {
        return "RefundRequest{" +
                "requestID=" + requestID +
                ", transcationID=" + transactionID +
                ", refundStatus=" + refundStatus +
                '}';
    }
}
