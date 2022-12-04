package refund;

import transactions.Transaction;

import java.util.List;

public class RefundRequestView {
    private final RefundRequestController refundRequestController;

    public RefundRequestView(RefundRequestController refundRequestController) {
        this.refundRequestController = refundRequestController;
    }

    public void createRefundRequest(int transactionID) {
        refundRequestController.createRefundRequest(transactionID);
    }

    public boolean listTransactions(List<Transaction> transactions) {
        if (transactions.size() > 0) {
            System.out.println("Select a transaction by its ID\n");
            for (Transaction t : transactions) {
                System.out.println(t.toString());
            }
            return true;
        } else {
            System.out.println("\nYou have not made any transactions yet\n");
            return false;
        }
    }
}



