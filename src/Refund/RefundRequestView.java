package Refund;

import org.omg.IOP.TransactionService;

import java.util.ArrayList;
import java.util.List;

public class RefundRequestView {
    private final RefundRequestController refundRequestController;
    private final List<Transaction> transactions=new ArrayList<>();

    public RefundRequestView(RefundRequestController refundRequestController) {
        this.refundRequestController = refundRequestController;
    }
}
