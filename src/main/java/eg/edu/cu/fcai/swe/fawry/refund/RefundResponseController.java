package eg.edu.cu.fcai.swe.fawry.refund;

import eg.edu.cu.fcai.swe.fawry.common.MissingFieldException;
import eg.edu.cu.fcai.swe.fawry.common.ResourceNotFound;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.transaction.InMemoryTransactionRepository;
import eg.edu.cu.fcai.swe.fawry.transaction.Transaction;
import eg.edu.cu.fcai.swe.fawry.transaction.TransactionRepository;
import eg.edu.cu.fcai.swe.fawry.wallet.InMemoryWalletRepository;
import eg.edu.cu.fcai.swe.fawry.wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/refundResponse")
public class RefundResponseController {
    private final RefundRepository refundRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public RefundResponseController(
            InMemoryRefundRepository _refundRepository,
            InMemoryWalletRepository _walletRepository,
            InMemoryTransactionRepository _transactionRepository
    ) {
        refundRepository = _refundRepository;
        walletRepository = _walletRepository;
        transactionRepository = _transactionRepository;
    }

    @PostMapping
    public RefundStatus setRequestStatus(@RequestBody RefundResponseForm form) {
        if (Validator.fieldDoesNotExist(form.requestId()))
            throw new MissingFieldException("requestId");

        if (Objects.isNull(form.refundStatus()))
            throw new MissingFieldException("refundStatus");

        RefundRequest refundRequest = refundRepository.setStatus(form.requestId(), form.refundStatus());

        if (Objects.isNull(refundRequest))
            throw new ResourceNotFound("Refund request", form.requestId());

        Transaction transaction = transactionRepository.getById(refundRequest.transactionId());

        if (Objects.isNull(transaction))
            throw new ResourceNotFound("Transaction", refundRequest.transactionId());

        walletRepository.updateUserBalance(refundRequest.userId(), transaction.amount());
        return form.refundStatus();
    }
}
