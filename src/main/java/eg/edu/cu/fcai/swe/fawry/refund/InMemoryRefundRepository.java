package eg.edu.cu.fcai.swe.fawry.refund;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryRefundRepository implements RefundRepository {
    private final List<RefundRequest> refundRequests = new ArrayList<>();

    @Override
    public void add(int transactionID, int userID) {
        refundRequests.add(new RefundRequest(this.refundRequests.size(), transactionID, userID, RefundStatus.Pending));
    }

    public List<RefundRequest> getAllRefundRequests() {
        return refundRequests;
    }

    @Override
    public void setRefundRequestStatus(int requestID, RefundStatus refundStatus) {
        for (int i = 0; i < refundRequests.size(); i++) {
            RefundRequest r = refundRequests.get(i);
            if (r.requestID() == requestID) {
                refundRequests.set(i, new RefundRequest(r.requestID(), r.transactionID(), r.userID(), refundStatus));
//                TODO: Return money to user wallet
                break;
            }
        }
    }

    @Override
    public List<RefundRequest> getRefundRequestsByUserID(int userID) {
        return this.refundRequests.stream().filter(refundRequest -> refundRequest.userID() == userID).toList();
    }

    @Override
    public int count() {
        return this.refundRequests.size();
    }
}
