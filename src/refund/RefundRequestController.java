package refund;

import auth.User;
import transactions.Transaction;
import transactions.TransactionRepository;

import java.util.List;

public class RefundRequestController {
    public final RefundRepository refundRepository;
    public final TransactionRepository transactionRepository;
    public final User currentUser;

    public RefundRequestController(RefundRepository refundRepository, TransactionRepository transactionRepository, User user) {
        this.refundRepository = refundRepository;
        this.transactionRepository = transactionRepository;
        currentUser = user;
    }

    public void createRefundRequest(int transactionID) {
        refundRepository.add(transactionID, currentUser.id());
    }

    public List<Transaction> getAllUserTransactions() {
        return transactionRepository.getTransactionsByUserID(currentUser.id());
    }
}
