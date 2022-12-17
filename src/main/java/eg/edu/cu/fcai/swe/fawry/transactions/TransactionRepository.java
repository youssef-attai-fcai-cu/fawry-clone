package eg.edu.cu.fcai.swe.fawry.transactions;

import java.util.List;

public interface TransactionRepository {
    Transaction createNew(int userID, float billAmount, String serviceName);

    List<Transaction> getAllTransactions();

    List<Transaction> getTransactionsByUserID(int userID);

    int count();
}
