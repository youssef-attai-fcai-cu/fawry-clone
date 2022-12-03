package Refund;

import java.lang.reflect.Array;
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
}
