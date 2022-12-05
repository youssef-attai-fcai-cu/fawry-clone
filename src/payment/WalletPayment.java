package payment;

public class WalletPayment implements PaymentMethod {
    private final float userWalletBalance;

    public WalletPayment(float walletBalance) {
        this.userWalletBalance = walletBalance;
    }

    @Override
    public boolean pay(float amount) {
        return !(userWalletBalance < amount);
    }
}
