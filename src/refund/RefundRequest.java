package refund;

public class RefundRequest {
    public final int requestID;
    public final int transcationID;

    public void setRefundStatus(RefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    private   RefundStatus refundStatus;


    public RefundRequest(int requestID, int transcationID, RefundStatus refundStatus) {
        this.requestID = requestID;
        this.transcationID = transcationID;
        this.refundStatus = refundStatus;
    }

    @Override
    public String toString() {
        return "RefundRequest{" +
                "requestID=" + requestID +
                ", transcationID=" + transcationID +
                ", refundStatus=" + refundStatus +
                '}';
    }
}
