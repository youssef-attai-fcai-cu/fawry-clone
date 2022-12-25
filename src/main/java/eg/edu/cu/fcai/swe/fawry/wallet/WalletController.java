package eg.edu.cu.fcai.swe.fawry.wallet;

import eg.edu.cu.fcai.swe.fawry.auth.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.auth.User;
import eg.edu.cu.fcai.swe.fawry.auth.UserRepository;
import eg.edu.cu.fcai.swe.fawry.common.Response;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.transaction.InMemoryTransactionRepository;
import eg.edu.cu.fcai.swe.fawry.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public WalletController(
            InMemoryUserRepository _userRepository,
            InMemoryWalletRepository _walletRepository,
            InMemoryTransactionRepository _transactionRepository
    ) {
        userRepository = _userRepository;
        walletRepository = _walletRepository;
        transactionRepository = _transactionRepository;
    }

    @GetMapping
    public Response checkCurrentBalance(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = Validator.validateUserToken(userRepository, token);
        return new Response(walletRepository.getUserBalance(user.userId()));
    }

    @PostMapping
    public Response addFunds(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody AddFundsForm form) {
        User user = Validator.validateUserToken(userRepository, token);

        Validator.assertFieldExists("amount", form.amount());
        Validator.assertNumberWithinRange("amount", form.amount(), 0.0f, Float.MAX_VALUE);

        float newBalance = walletRepository.updateUserBalance(user.userId(), form.amount());

        transactionRepository.create(user.userId(), form.amount(), "Add funds to wallet");
        return new Response("Added $" + form.amount() + " to your wallet. Your balance is now: $" + newBalance);
    }
}