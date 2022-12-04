package refund;

import java.util.List;

public interface RefundRepository {
    void add(int transactionID, int userID);
    List<RefundRequest> getAllRefundRequests();
    void setRefundRequestStatus(int requestID, RefundStatus refundStatus);

    List<RefundRequest> getRefundRequestsByUserID(int userID);

    int count();
}

