package Refund;

import java.util.List;

public interface RefundRepository {
    void add(RefundRequest refundRequest);
    List<RefundRequest> gettAllRefundRequests();
}
