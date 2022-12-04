package refund;

import transactions.Transaction;
import transactions.TransactionRepository;

import java.util.List;

public class RefundRequestController {
    private final RefundRepository refundRepository;
    private final TransactionRepository transactionRepository;
    private final int userID;

    public RefundRequestController(RefundRepository refundRepository, TransactionRepository transactionRepository, int userID) {
        this.refundRepository = refundRepository;
        this.transactionRepository = transactionRepository;
        this.userID = userID;
    }
    public void createRefundRequest(int transactionID)
    {
        refundRepository.add(new RefundRequest(0,transactionID,RefundStatus.Pending));
        System.out.println("refund request");
    }
    public List<Transaction> getUserTransaction(){
        return transactionRepository.getTransactionsByUserID(userID);
    }
}
