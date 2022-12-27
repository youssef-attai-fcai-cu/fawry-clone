package eg.edu.cu.fcai.swe.fawry.refund.model;

import eg.edu.cu.fcai.swe.fawry.refund.RefundStatus;

public record RefundRequest(String transactionId, RefundStatus refundStatus) {
}
