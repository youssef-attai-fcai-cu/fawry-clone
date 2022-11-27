package payment;

public class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println(amount + " Credit Card strat");
    }
}
