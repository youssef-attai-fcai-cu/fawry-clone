package payment;

public class WalletPayment implements PaymentMethod {
    @Override
    public void pay(float amount) {
        System.out.println("wallet payment");
    }
}
