package eg.edu.cu.fcai.swe.fawry.transactions;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryTransactionRepository implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public Transaction createNew(int userID, float billAmount, String serviceName) {
        Transaction newTransaction = new Transaction(userID, transactions.size(), billAmount, serviceName);
        transactions.add(newTransaction);
        return newTransaction;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return this.transactions;
    }

    @Override
    public List<Transaction> getTransactionsByUserID(int userID) {
        return this.transactions.stream().filter(transaction -> transaction.userID() == userID).toList();
    }

    @Override
    public int count() {
        return this.transactions.size();
    }
}
