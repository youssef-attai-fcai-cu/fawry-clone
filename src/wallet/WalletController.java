package wallet;

import auth.User;
import auth.UserRepository;

public class WalletController {
    private final UserRepository userRepository;
    public final User currentUser;

    public WalletController(UserRepository repository, User user) {
        userRepository = repository;
        currentUser = user;
    }

    public void addWalletBalance(float funds) {
        userRepository.updateWalletBalance(currentUser.id(), funds);
    }
}
