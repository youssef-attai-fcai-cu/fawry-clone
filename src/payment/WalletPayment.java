package payment;

import auth.UserRepository;

public class WalletPayment implements PaymentMethod {
    private final UserRepository userRepository;
    private final int currentUserID;

    public WalletPayment(UserRepository userRepository, int currentUserID) {
        this.userRepository = userRepository;
        this.currentUserID = currentUserID;
    }

    @Override
    public boolean pay(float amount) {
        if (userRepository.getUserWalletBalance(currentUserID) < amount) {
            return false;
        } else {
            userRepository.updateWalletBalance(currentUserID, -1 * amount);
            return true;
        }
    }
}
