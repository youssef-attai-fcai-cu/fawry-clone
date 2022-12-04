package Refund;

import java.util.List;

public class RefundResponseController {
    private final RefundRepository refundRepository;

    public RefundResponseController(RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
    }
    public List<RefundRequest> getAllRefundRequests()
    {
        return refundRepository.gettAllRefundRequests();
    }
    public void setRefundRequestStatus(int requestID, RefundStatus refundStatus)
    {

    }
}
