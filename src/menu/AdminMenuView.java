package menu;

import core.View;
import fawry.Fawry;

public class AdminMenuView extends View {
    public final Fawry fawry;

    public AdminMenuView(Fawry fawry) {
        this.fawry = fawry;
    }

    public void show() {
        System.out.println("1. Manage discounts");
        System.out.println("2. Manage refund requests");
        System.out.println("3. Logout");
        int choice = inputWithinRange("> ", 1, 3);
        switch (choice) {
            case 1 -> fawry.manageDiscounts();
            case 2 -> fawry.manageRefundRequests();
            case 3 -> fawry.logout();
            default -> System.out.println("Invalid option");
        }
    }
}
