package eg.edu.cu.fcai.swe.fawry.refund;

public record RefundRequest(int requestID, int transactionID, int userID, RefundStatus refundStatus) {
}
