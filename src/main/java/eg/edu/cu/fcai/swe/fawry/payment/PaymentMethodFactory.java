package eg.edu.cu.fcai.swe.fawry.payment;

import eg.edu.cu.fcai.swe.fawry.wallet.WalletRepository;

public class PaymentMethodFactory {
    private final WalletRepository walletRepository;
    private final String userId;

    public PaymentMethodFactory(WalletRepository _walletRepository, String _userId) {
        walletRepository = _walletRepository;
        userId = _userId;
    }

    public PaymentMethod create(PaymentOption method) {
        return switch (method) {
            case WALLET -> new WalletPayment(walletRepository, userId);
            case CASH -> new CashOnDeliveryPayment();
            default -> new CreditCardPayment();
        };
    }
}
