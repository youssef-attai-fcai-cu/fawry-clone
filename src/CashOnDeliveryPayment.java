public class CashOnDeliveryPayment implements PaymentMethod{
    @Override
    public void pay(int amount) {
        System.out.println("cash on delivery strat");
    }
}
