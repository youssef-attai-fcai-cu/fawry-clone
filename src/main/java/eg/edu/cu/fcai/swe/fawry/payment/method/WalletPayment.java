package eg.edu.cu.fcai.swe.fawry.payment.method;

import eg.edu.cu.fcai.swe.fawry.payment.exception.InsufficientWalletBalance;
import eg.edu.cu.fcai.swe.fawry.common.repository.WalletRepository;

public class WalletPayment implements PaymentMethod {
    private final WalletRepository walletRepository;
    private final String userId;

    public WalletPayment(WalletRepository _walletRepository, String _userId) {
        walletRepository = _walletRepository;
        userId = _userId;
    }

    @Override
    public boolean pay(float amount) {
        if (walletRepository.getUserBalance(userId) < amount)
            throw new InsufficientWalletBalance();
        walletRepository.updateUserBalance(userId, -amount);
        return true;
    }
}
