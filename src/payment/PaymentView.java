package payment;

import java.util.Scanner;

public class PaymentView {
    private final Scanner scanner = new Scanner(System.in);
    private final PaymentController controller;

    public PaymentView(PaymentController controller) {
        this.controller = controller;
//        this.payCreditCardButton.setClickListener(() -> this.controller.setPaymentMethod(new CreditCardPayment()));
//        this.payWalletButton.setClickListener(() -> this.controller.setPaymentMethod(new WalletPayment()));
//        this.payCashOnDeliveryButton.setClickListener(() -> this.controller.setPaymentMethod(new CashOnDeliveryPayment()));
//        this.submitButton.setClickListener(this.controller::submitPaymentForm);
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod) {
        this.controller.setPaymentMethod(paymentMethod);
    }

    public void fillPaymentForm() {
        this.controller.getServiceProvider();
    }
}
