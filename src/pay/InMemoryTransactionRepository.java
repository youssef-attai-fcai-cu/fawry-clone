package pay;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();


    @Override
    public void createNew(int userID, float billAmount, String serviceName) {
        transactions.add(new Transaction(userID, transactions.size(), billAmount, serviceName));
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return this.transactions;
    }
}
