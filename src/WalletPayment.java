public class WalletPayment implements PaymentMethod{
    @Override
    public void pay(int amount) {
        System.out.println("wallet payment");
    }
}
