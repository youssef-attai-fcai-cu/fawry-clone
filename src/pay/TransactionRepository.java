package pay;

import java.util.List;

public interface TransactionRepository {
    void createNew(int userID, float billAmount, String serviceName);

    List<Transaction> getAllTransactions();
}
