package Refund;

import org.omg.IOP.TransactionService;
import ui_utils.ButtonView;

import java.util.ArrayList;
import java.util.List;

public class RefundRequestView {
    private final RefundRequestController refundRequestController;
    private  int selectedTransactions;
    private final ButtonView requestRefund = new ButtonView();

    public RefundRequestView(RefundRequestController refundRequestController) {
        this.refundRequestController = refundRequestController;
        for (Transaction t : refundRequestController.getUserTransaction()) {
            System.out.println(t.toString());
        }
        requestRefund.setClickListener(()->{
            refundRequestController.createRefundRequest(selectedTransactions);
        });
    }

    public void setSelectedTransactions(int selectedTransactions) {

        this.selectedTransactions = selectedTransactions;
    }
    public void ClickButton(){
        requestRefund.click();
    }
}



