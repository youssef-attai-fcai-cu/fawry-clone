package eg.edu.cu.fcai.swe.fawry.refund.controller;

import eg.edu.cu.fcai.swe.fawry.auth.repository.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.common.repository.RefundRepository;
import eg.edu.cu.fcai.swe.fawry.common.repository.UserRepository;
import eg.edu.cu.fcai.swe.fawry.common.exception.ResourceNotFound;
import eg.edu.cu.fcai.swe.fawry.common.Response;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.refund.repository.InMemoryRefundRepository;
import eg.edu.cu.fcai.swe.fawry.refund.model.RefundRequest;
import eg.edu.cu.fcai.swe.fawry.refund.model.RefundStatus;
import eg.edu.cu.fcai.swe.fawry.refund.request.RefundResponseRequestBody;
import eg.edu.cu.fcai.swe.fawry.transaction.repository.InMemoryTransactionRepository;
import eg.edu.cu.fcai.swe.fawry.transaction.model.Transaction;
import eg.edu.cu.fcai.swe.fawry.common.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/refund")
public class RefundResponseController {
    private final RefundRepository refundRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public RefundResponseController(
            InMemoryUserRepository _userRepository,
            InMemoryRefundRepository _refundRepository,
            InMemoryTransactionRepository _transactionRepository
    ) {
        userRepository = _userRepository;
        refundRepository = _refundRepository;
        transactionRepository = _transactionRepository;
    }

    @GetMapping("/all")
    public List<RefundRequest> getAllRefundRequests(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        Validator.validateAdminToken(userRepository, token);
        return refundRepository.getAll();
    }

    @PostMapping("/response")
    public Response setRequestStatus(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody RefundResponseRequestBody form) {
        Validator.validateAdminToken(userRepository, token);

        Validator.assertFieldExists("transactionId", form.transactionId());
        Validator.assertFieldExists("refundStatus", form.refundStatus().ordinal());

        RefundRequest refundRequest = refundRepository.setStatus(form.transactionId(), form.refundStatus());

        if (Objects.isNull(refundRequest))
            throw new ResourceNotFound("Transaction", form.transactionId());

        Transaction transaction = transactionRepository.getById(refundRequest.transactionId());

        if (Objects.isNull(transaction))
            throw new ResourceNotFound("Transaction", refundRequest.transactionId());

        if (refundRequest.refundStatus().equals(RefundStatus.Accepted))
            transactionRepository.create(transaction.userId(), transaction.amount(), "Refund");

        return new Response("Refund request for transaction with ID " + refundRequest.transactionId() + " is now " + refundRequest.refundStatus().name());
    }
}
