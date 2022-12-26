package eg.edu.cu.fcai.swe.fawry.transaction.controller;

import eg.edu.cu.fcai.swe.fawry.auth.repository.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.auth.model.User;
import eg.edu.cu.fcai.swe.fawry.common.repository.TransactionRepository;
import eg.edu.cu.fcai.swe.fawry.common.repository.UserRepository;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.transaction.repository.InMemoryTransactionRepository;
import eg.edu.cu.fcai.swe.fawry.transaction.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    public final TransactionRepository transactionRepository;
    public final UserRepository userRepository;

    @Autowired
    public TransactionController(InMemoryTransactionRepository _transactionRepository, InMemoryUserRepository _userRepository) {
        transactionRepository = _transactionRepository;
        userRepository = _userRepository;
    }

    @GetMapping
    public List<Transaction> getUserTransactions(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = Validator.validateUserToken(userRepository, token);
        return transactionRepository.getByUserId(user.userId());
    }
    @GetMapping("/all")
    public List<Transaction> getAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        Validator.validateAdminToken(userRepository, token);
        return transactionRepository.getAll();
    }
}
