package eg.edu.cu.fcai.swe.fawry.refund;

import java.util.List;

public interface RefundRepository {
    RefundRequest create(String transactionId);

    RefundRequest setStatus(String transactionId, RefundStatus refundStatus);

    List<RefundRequest> getAll();

    List<RefundRequest> getRefundRequestsForTransactions(List<String> transactionIds);
}

