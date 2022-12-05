package payment;

public class CashOnDeliveryPayment implements PaymentMethod {
    @Override
    public boolean pay(float amount) {
        return true;
    }
}
