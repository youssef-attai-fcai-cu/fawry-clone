package menu;

import core.View;
import fawry.Fawry;

public class UserMenuView extends View {
    public final Fawry fawry;

    public UserMenuView(Fawry fawry) {
        this.fawry = fawry;
    }

    public void show() {
        System.out.println("1. Search for services");
        System.out.println("2. Make a refund request");
        System.out.println("3. See my refund requests");
        System.out.println("4. Add funds to wallet");
        System.out.println("5. Logout");
        int choice = inputWithinRange("> ", 1, 5);
        switch (choice) {
            case 1 -> fawry.searchForServices();
            case 2 -> fawry.requestRefund();
            case 3 -> fawry.myRequestRequests();
            case 4 -> fawry.addFundsToWallet();
            case 5 -> fawry.logout();
            default -> System.out.println("Invalid option");
        }
    }
}
