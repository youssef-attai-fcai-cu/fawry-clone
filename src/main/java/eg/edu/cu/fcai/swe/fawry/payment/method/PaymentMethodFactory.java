package eg.edu.cu.fcai.swe.fawry.payment.method;

import eg.edu.cu.fcai.swe.fawry.common.repository.WalletRepository;

public class PaymentMethodFactory {
    private final WalletRepository walletRepository;
    private final String userId;

    public PaymentMethodFactory(WalletRepository _walletRepository, String _userId) {
        walletRepository = _walletRepository;
        userId = _userId;
    }

    public PaymentMethod create(PaymentOption option) {
        return switch (option) {
            case WALLET -> new WalletPayment(walletRepository, userId);
            case CASH -> new CashOnDeliveryPayment();
            default -> new CreditCardPayment();
        };
    }
}
