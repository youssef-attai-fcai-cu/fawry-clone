package eg.edu.cu.fcai.swe.fawry.refund;

import eg.edu.cu.fcai.swe.fawry.transactions.InMemoryTransactionRepository;
import eg.edu.cu.fcai.swe.fawry.transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refundRequest")
public class RefundRequestController {
    public final InMemoryRefundRepository refundRepository;
    public final InMemoryTransactionRepository transactionRepository;

    @Autowired
    public RefundRequestController(InMemoryRefundRepository refundRepository, InMemoryTransactionRepository transactionRepository) {
        this.refundRepository = refundRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping
    public void createRefundRequest(@RequestBody RefundRequestForm form) {
        refundRepository.add(form.transactionID(), form.uid());
    }

    @GetMapping("/{uid}")
    public List<Transaction> getAllUserTransactions(@PathVariable Integer uid) {
        return transactionRepository.getTransactionsByUserID(uid);
    }
}
