package eg.edu.cu.fcai.swe.fawry.refund;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class InMemoryRefundRepository implements RefundRepository {
    private final List<RefundRequest> refundRequests = new ArrayList<>();

    @Override
    public RefundRequest create(String transactionId, String userId) {
        if (!Objects.isNull(refundRequests.stream().filter(refundRequest -> refundRequest.transactionId().equals(transactionId)).findFirst().orElse(null)))
            return null;
        RefundRequest newRefundRequest = new RefundRequest("REFUND_REQUEST-" + UUID.randomUUID(), transactionId, userId, RefundStatus.Pending);
        refundRequests.add(newRefundRequest);
        return newRefundRequest;
    }

    @Override
    public RefundRequest setStatus(String requestId, RefundStatus refundStatus) {
        for (int i = 0; i < refundRequests.size(); i++) {
            RefundRequest r = refundRequests.get(i);
            if (r.requestId().equals(requestId)) {
                refundRequests.set(i, new RefundRequest(r.requestId(), r.transactionId(), r.userId(), refundStatus));
                return refundRequests.get(i);
            }
        }
        return null;
    }

    @Override
    public List<RefundRequest> getAll() {
        return refundRequests;
    }
}
