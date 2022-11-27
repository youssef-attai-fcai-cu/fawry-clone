package payment;

import payment.PaymentMethod;

public class WalletPayment implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println(amount + " Wallet payment ");
    }
}
