package eg.edu.cu.fcai.swe.fawry.transaction.repository;

import eg.edu.cu.fcai.swe.fawry.common.repository.TransactionRepository;
import eg.edu.cu.fcai.swe.fawry.transaction.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InMemoryTransactionRepository implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();


    @Override
    public Transaction create(String userId, Float billAmount, String serviceName) {
        Transaction newTransaction = new Transaction("TRANSACTION-" + UUID.randomUUID(), userId, billAmount, serviceName);
        transactions.add(newTransaction);
        return newTransaction;
    }

    @Override
    public List<Transaction> getByUserId(String userID) {
        return this.transactions.stream().filter(transaction -> transaction.userId().equals(userID)).toList();
    }

    @Override
    public Transaction getById(String transactionId) {
        return transactions.stream().filter(transaction -> transaction.transactionId().equals(transactionId)).findFirst().orElse(null);
    }

    @Override
    public List<Transaction> getAll() {
        return transactions;
    }
}
