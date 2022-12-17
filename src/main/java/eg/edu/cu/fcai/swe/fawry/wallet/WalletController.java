package eg.edu.cu.fcai.swe.fawry.wallet;

import eg.edu.cu.fcai.swe.fawry.auth.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final UserRepository userRepository;

    public WalletController(UserRepository repository) {
        userRepository = repository;
    }

    @PostMapping
    public void addWalletBalance(@RequestBody AddWalletBalanceForm form) {
        userRepository.updateWalletBalance(form.uid(), form.funds());
    }
}
