package refund;

import core.View;

import java.util.List;

public class RefundResponseView extends View {
    public final RefundResponseController refundResponseController;

    public RefundResponseView(RefundResponseController refundResponseController) {
        this.refundResponseController = refundResponseController;
    }

    public void show() {
        List<RefundRequest> requests = refundResponseController.getAllRefundRequests();
        if (requests.size() > 0) {
            for (RefundRequest r : requests) System.out.println(r.toString());
            int requestID = inputInteger("Select a refund request by its ID: ");
            System.out.println("Request state:");
            System.out.println("1. Accept");
            System.out.println("2. Reject");
            System.out.println("3. Keep pending");
            refundResponseController.setRefundRequestStatus(
                    requestID,
                    switch (inputWithinRange("Mark request as: ", 1, 3)) {
                        case 1 -> RefundStatus.Accepted;
                        case 2 -> RefundStatus.Rejected;
                        default -> RefundStatus.Pending;
                    }
            );
        } else System.out.println("There are no requested refunds");
    }
}
