package payment;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public boolean pay(float amount) {
        return true;
    }
}
