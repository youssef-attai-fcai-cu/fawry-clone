package eg.edu.cu.fcai.swe.fawry.payment;

public class PaymentMethodFactory {
    public PaymentMethod create(PaymentOption method) {
        return switch (method) {
            case WALLET -> new WalletPayment();
            case CASH -> new CashOnDeliveryPayment();
            default -> new CreditCardPayment();
        };
    }
}
