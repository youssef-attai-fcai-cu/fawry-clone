package eg.edu.cu.fcai.swe.fawry.refund.request;

import eg.edu.cu.fcai.swe.fawry.refund.RefundStatus;

public record RefundResponseRequestBody(
        String transactionId,
        RefundStatus refundStatus
) {
}
