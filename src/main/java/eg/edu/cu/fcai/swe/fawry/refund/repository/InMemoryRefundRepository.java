package eg.edu.cu.fcai.swe.fawry.refund.repository;


import eg.edu.cu.fcai.swe.fawry.common.repository.RefundRepository;
import eg.edu.cu.fcai.swe.fawry.refund.model.RefundRequest;
import eg.edu.cu.fcai.swe.fawry.refund.model.RefundStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class InMemoryRefundRepository implements RefundRepository {
    private final List<RefundRequest> refundRequests = new ArrayList<>();

    @Override
    public RefundRequest create(String transactionId) {
        if (!Objects.isNull(refundRequests.stream().filter(refundRequest -> refundRequest.transactionId().equals(transactionId)).findFirst().orElse(null)))
            return null;
        RefundRequest newRefundRequest = new RefundRequest(transactionId, RefundStatus.Pending);
        refundRequests.add(newRefundRequest);
        return newRefundRequest;
    }

    @Override
    public RefundRequest setStatus(String transactionId, RefundStatus refundStatus) {
        for (int i = 0; i < refundRequests.size(); i++) {
            RefundRequest r = refundRequests.get(i);
            if (r.transactionId().equals(transactionId)) {
                refundRequests.set(i, new RefundRequest(r.transactionId(), refundStatus));
                return refundRequests.get(i);
            }
        }
        return null;
    }

    @Override
    public List<RefundRequest> getAll() {
        return refundRequests;
    }

    @Override
    public List<RefundRequest> getRefundRequestsForTransactions(List<String> transactionIds) {
        List<RefundRequest> result = new ArrayList<>();
        for (RefundRequest refundRequest : refundRequests)
            if (transactionIds.contains(refundRequest.transactionId()))
                result.add(refundRequest);
        return result;
    }
}
