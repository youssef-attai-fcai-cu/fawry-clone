package refund;

import core.View;
import transactions.Transaction;

import java.util.List;

public class RefundRequestView extends View {
    public final RefundRequestController refundRequestController;

    public RefundRequestView(RefundRequestController controller) {
        refundRequestController = controller;
    }

    public void createRefundRequest(int transactionID) {
        refundRequestController.createRefundRequest(transactionID);
    }

    public int selectTransaction() {
        List<Transaction> transactions = refundRequestController.getAllUserTransactions();
        if (transactions.size() > 0) {
            for (Transaction t : transactions) System.out.println(t.toString());
            return inputInteger("Select a transaction by its ID: ");
        } else {
            System.out.println("You have no transactions");
            return -1;
        }
    }

    public void listRefundRequests() {
        if (refundRequestController.refundRepository.count() > 0) {
            for (RefundRequest r :
                    refundRequestController.refundRepository.getRefundRequestsByUserID(refundRequestController.currentUser.id())) {
                System.out.println(r.toString());
            }
        } else {
            System.out.println("You have not requested any refunds");
        }
    }
}



