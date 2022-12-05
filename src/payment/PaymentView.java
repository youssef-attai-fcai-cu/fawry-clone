package payment;

import core.View;
import service.PaymentFormField;

import java.util.Map;

public class PaymentView extends View {
    private final PaymentController paymentController;

    public PaymentView(PaymentController controller) {
        paymentController = controller;
    }

    public void selectPaymentMethod() {
        System.out.println("Choose a payment method:");
        System.out.println("1. Credit card");
        System.out.println("2. Wallet");
        boolean allowsCashOnDelivery = paymentController.serviceProvider.allowsCashOnDelivery();
        if (allowsCashOnDelivery) System.out.println("3. Cash on delivery");

        int choice = inputWithinRange("Select method number: ", 1, 2 + (allowsCashOnDelivery ? 1 : 0));

        paymentController.setPaymentMethod(switch (choice) {
            case 2 -> new WalletPayment(paymentController.currentUser.walletBalance());
            case 3 -> new CashOnDeliveryPayment();
            default -> new CreditCardPayment();
        });
    }

    public String inputField(PaymentFormField field) {
        return inputString(field.label() + ": ");
    }

    public void submitForm(Map<String, String> form) {
        if (paymentController.submitPaymentForm(form)) {
            System.out.println("Successful transaction");
        } else {
            System.out.println("Something went wrong, transaction is incomplete");
        }
    }
}
