package wallet;

import java.util.Scanner;

public class WalletView {
    private WalletController controller;


    public WalletView(WalletController controller) {
        this.controller = controller;
    }

    public void addFunds(float funds) {
        this.controller.addWalletBalance(funds);
    }
}
