package eg.edu.cu.fcai.swe.fawry.refund.model;

public record RefundRequest(String transactionId, RefundStatus refundStatus) {
}
