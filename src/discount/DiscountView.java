package discount;

import core.View;

public class DiscountView extends View {
    public final DiscountController discountController;

    public DiscountView(DiscountController controller) {
        discountController = controller;
    }

    private int selectService() {
        for (int i = 0; i < discountController.allServices.size(); i++)
            System.out.println((i + 1) + ". " + discountController.allServices.get(i).getServiceName());
        System.out.println("0. All services");
        return inputWithinRange("Add a discount on: ", 0, discountController.allServices.size());
    }

    public void show() {
        int choice = selectService();
        int percentage = inputWithinRange("Discount percentage: ", 0, 100);
        if (choice == 0) discountController.addNewOverallDiscount(percentage);
        else discountController.addNewSpecificDiscount(percentage, choice);
    }
}
