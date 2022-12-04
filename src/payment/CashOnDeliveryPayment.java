package payment;

public class CashOnDeliveryPayment implements PaymentMethod {
    @Override
    public void pay(float amount) {
        System.out.println("cash on delivery strat");
    }
}
