package eg.edu.cu.fcai.swe.fawry.refund;

import eg.edu.cu.fcai.swe.fawry.common.MissingFieldException;
import eg.edu.cu.fcai.swe.fawry.common.ResourceNotFound;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.transaction.Transaction;
import eg.edu.cu.fcai.swe.fawry.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/refundRequest")
public class RefundRequestController {
    public final RefundRepository refundRepository;
    public final TransactionRepository transactionRepository;

    @Autowired
    public RefundRequestController(InMemoryRefundRepository _refundRepository, TransactionRepository _transactionRepository) {
        refundRepository = _refundRepository;
        transactionRepository = _transactionRepository;
    }

    @GetMapping
    public List<RefundRequest> getAllRefundRequests() {
        return refundRepository.getAll();
    }

    @PostMapping
    public RefundRequest createRefundRequest(@RequestBody RefundRequestForm form) {
        if (Validator.fieldDoesNotExist(form.transactionId()))
            throw new MissingFieldException("transactionId");

        Transaction transaction = transactionRepository.getById(form.transactionId());

        if (Objects.isNull(transaction))
            throw new ResourceNotFound("Transaction", form.transactionId());

        // Make sure the transaction belongs to the signed-in user
        if (!transaction.userId().equals(user.userId()))
            throw new ResourceNotFound("Transaction", form.transactionId());

        RefundRequest newRefundRequest = refundRepository.create(form.transactionId());

        if (Objects.isNull(newRefundRequest))
            throw new RefundAlreadyRequested(transaction.transactionId());

        return newRefundRequest;
    }
}
