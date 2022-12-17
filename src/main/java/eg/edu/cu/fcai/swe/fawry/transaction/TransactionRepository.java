package eg.edu.cu.fcai.swe.fawry.transaction;

import java.util.List;

public interface TransactionRepository {
    Transaction create(String userId, Float billAmount, String serviceName);

    List<Transaction> getByUserId(String userID);

    Transaction getById(String transactionId);
}
