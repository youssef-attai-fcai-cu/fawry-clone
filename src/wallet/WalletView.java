package wallet;

import core.View;

public class WalletView extends View {
    public final WalletController walletController;

    public WalletView(WalletController controller) {
        walletController = controller;
    }

    public void show() {
        System.out.println("Your current wallet balance: " + walletController.currentUser.walletBalance());
        walletController.addWalletBalance(inputFloat("Amount to add: "));
    }
}
