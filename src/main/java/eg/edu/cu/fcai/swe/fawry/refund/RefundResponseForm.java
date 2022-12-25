package eg.edu.cu.fcai.swe.fawry.refund;

public record RefundResponseForm(
        String transactionId,
        RefundStatus refundStatus
) {
}
