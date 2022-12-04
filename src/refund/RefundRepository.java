package refund;

import java.util.List;

public interface RefundRepository {
    void add(RefundRequest refundRequest);
    List<RefundRequest> gettAllRefundRequests();
    void setRefundRequestStatus(int requestID, RefundStatus refundStatus);
}

