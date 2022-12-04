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

    public void createRefundRequest(int transactionID) {
        refundRepository.add(transactionID, this.userID);
    }

    public List<Transaction> getAllUserTransactions() {
        return transactionRepository.getTransactionsByUserID(userID);
    }
}
