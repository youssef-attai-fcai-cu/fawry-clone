package payment;

public class CashOnDeliveryPayment implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println(amount + " cash on delivery");
    }
}
