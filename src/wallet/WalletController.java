package wallet;

import auth.UserRepository;

public class WalletController {
    private UserRepository userRepository;
    private int currentUserID;

    public WalletController(UserRepository userRepository, int currentUserID) {
        this.userRepository = userRepository;
        this.currentUserID = currentUserID;
    }

    public void addWalletBalance(float funds) {
        userRepository.updateWalletBalance(currentUserID, funds);
    }
}
