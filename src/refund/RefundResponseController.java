package refund;

import java.util.List;

public class RefundResponseController {
    private final RefundRepository refundRepository;
    public RefundResponseController(RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
    }
    public List<RefundRequest> getAllRefundRequests()
    {
        return refundRepository.getAllRefundRequests();
    }
    public void setRefundRequestStatus(int requestID, RefundStatus refundStatus) {
//        TODO: Allow admin to set the refund request status
    }
}
