package Refund;

public class RefundResponseView {
    private final RefundResponseController refundResponseController;

    public RefundResponseView(RefundResponseController refundResponseController) {
        this.refundResponseController = refundResponseController;
        for (RefundRequest r:refundResponseController.getAllRefundRequests()) {
            System.out.println(r.toString());
        };
    }
    public void setRefundStatus(int requestID,RefundStatus refundStatus)
    {
        refundResponseController.setRefundRequestStatus(requestID,refundStatus);
    }
}
