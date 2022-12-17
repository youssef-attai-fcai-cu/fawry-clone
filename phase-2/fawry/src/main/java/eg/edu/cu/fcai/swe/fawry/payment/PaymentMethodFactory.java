package eg.edu.cu.fcai.swe.fawry.payment;

public class PaymentMethodFactory {
    public PaymentMethod create(Integer method) {
        return switch (method) {
            case 1 -> new WalletPayment();
            case 2 -> new CashOnDeliveryPayment();
            default -> new CreditCardPayment();
        };
    }
}
