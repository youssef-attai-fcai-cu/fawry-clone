package payment;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(float amount) {
        System.out.println("Credit Card strat");
    }
}
