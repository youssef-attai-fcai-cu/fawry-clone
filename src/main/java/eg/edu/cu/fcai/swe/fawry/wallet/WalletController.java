package eg.edu.cu.fcai.swe.fawry.wallet;

import eg.edu.cu.fcai.swe.fawry.common.InvalidAmount;
import eg.edu.cu.fcai.swe.fawry.common.MissingFieldException;
import eg.edu.cu.fcai.swe.fawry.common.ResourceNotFound;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.transaction.InMemoryTransactionRepository;
import eg.edu.cu.fcai.swe.fawry.transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public WalletController(InMemoryWalletRepository _walletRepository, InMemoryTransactionRepository _transactionRepository) {
        walletRepository = _walletRepository;
        transactionRepository = _transactionRepository;
    }

    @GetMapping
    public Response checkCurrentBalance(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = Validator.validateUserToken(userRepository, token);
        return new Response(walletRepository.getUserBalance(user.userId()));
    }

    @PostMapping
    public Float addWalletBalance(@RequestBody AddWalletBalanceForm form) {
        if (Validator.fieldDoesNotExist(form.userId()))
            throw new MissingFieldException("userId");

        if (form.amount() == null)
            throw new MissingFieldException("amount");

        if (form.amount() <= 0.0f)
            throw new InvalidAmount(form.amount());

        Float newBalance = walletRepository.updateUserBalance(form.userId(), form.amount());

        if (newBalance == null)
            throw new ResourceNotFound("User", form.userId());

        transactionRepository.create(form.userId(), form.amount(), "Add funds to wallet");
        return newBalance;
    }
}
