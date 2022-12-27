package eg.edu.cu.fcai.swe.fawry.common.repository;

import eg.edu.cu.fcai.swe.fawry.refund.model.RefundRequest;
import eg.edu.cu.fcai.swe.fawry.refund.RefundStatus;

import java.util.List;

public interface RefundRepository {
    RefundRequest create(String transactionId);

    RefundRequest setStatus(String transactionId, RefundStatus refundStatus);

    List<RefundRequest> getAll();

    List<RefundRequest> getRefundRequestsForTransactions(List<String> transactionIds);
}

