package refund;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRefundRepository implements RefundRepository {
    private final List<RefundRequest> refundRequests=new ArrayList<>();


    @Override
    public void add(RefundRequest refundRequest) {
        refundRequests.add(refundRequest);
    }
    public List<RefundRequest> gettAllRefundRequests(){
        return refundRequests;
    }

    @Override
    public void setRefundRequestStatus(int requestID, RefundStatus refundStatus) {
        for (RefundRequest refundRequest: refundRequests) {
            if(refundRequest.requestID==requestID)
            {
                refundRequest.setRefundStatus(refundStatus);
                break;
            }

        }
    }
}
