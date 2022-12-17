package eg.edu.cu.fcai.swe.fawry.transaction;

import eg.edu.cu.fcai.swe.fawry.auth.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.auth.UserRepository;
import eg.edu.cu.fcai.swe.fawry.common.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping("/{userId}")
    public List<Transaction> getByUserId(@PathVariable String userId) {
        if (Objects.isNull(userRepository.getById(userId)))
            throw new ResourceNotFound("User", userId);
        return transactionRepository.getByUserId(userId);
    }
}
