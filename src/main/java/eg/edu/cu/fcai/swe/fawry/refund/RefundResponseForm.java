package eg.edu.cu.fcai.swe.fawry.refund;

public record RefundResponseForm(
        String requestId,
        RefundStatus refundStatus
) {
}
