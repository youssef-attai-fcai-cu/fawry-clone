package eg.edu.cu.fcai.swe.fawry.refund;

import java.util.List;

public interface RefundRepository {
    RefundRequest create(String transactionId, String userId);

    RefundRequest setStatus(String requestID, RefundStatus refundStatus);

    List<RefundRequest> getAll();
}

