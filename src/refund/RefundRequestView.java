package refund;

import transactions.Transaction;
import ui_utils.ButtonView;

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



