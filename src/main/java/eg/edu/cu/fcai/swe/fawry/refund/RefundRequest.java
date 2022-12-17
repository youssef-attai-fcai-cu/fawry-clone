package eg.edu.cu.fcai.swe.fawry.refund;

public record RefundRequest(String requestId, String transactionId, String userId, RefundStatus refundStatus) {
}
