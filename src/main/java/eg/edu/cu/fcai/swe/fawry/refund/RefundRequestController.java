package eg.edu.cu.fcai.swe.fawry.refund;

import eg.edu.cu.fcai.swe.fawry.auth.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.auth.User;
import eg.edu.cu.fcai.swe.fawry.auth.UserRepository;
import eg.edu.cu.fcai.swe.fawry.common.ResourceNotFound;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.transaction.Transaction;
import eg.edu.cu.fcai.swe.fawry.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/refund/request")
public class RefundRequestController {
    public final RefundRepository refundRepository;
    public final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


    @Autowired
    public RefundRequestController(
            InMemoryUserRepository _userRepository,
            InMemoryRefundRepository _refundRepository,
            TransactionRepository _transactionRepository
    ) {
        refundRepository = _refundRepository;
        transactionRepository = _transactionRepository;
        userRepository = _userRepository;
    }

    @GetMapping
    public List<RefundRequest> getUserRefundRequests(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = Validator.validateUserToken(userRepository, token);
        return refundRepository.getRefundRequestsForTransactions(transactionRepository.getByUserId(user.userId()).stream().map(Transaction::transactionId).toList());
    }

    @PostMapping
    public RefundRequest createRefundRequest(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody RefundRequestForm form) {
        User user = Validator.validateUserToken(userRepository, token);

        Validator.assertFieldExists("transactionId", form.transactionId());
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
