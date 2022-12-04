package pay;

import payment.CashOnDeliveryPayment;
import payment.CreditCardPayment;
import payment.WalletPayment;
import ui_utils.ButtonView;

public class PaymentView {
    private final ButtonView payCreditCardButton = new ButtonView();
    private final ButtonView payWalletButton = new ButtonView();
    private final ButtonView payCashOnDeliveryButton = new ButtonView();
    private final ButtonView submitButton = new ButtonView();
    private final PaymentFormView paymentFormView = new PaymentFormView();
    private final PaymentController controller;

    public PaymentView(PaymentController controller) {
        this.controller = controller;
        this.payCreditCardButton.setClickListener(() -> this.controller.setPaymentMethod(new CreditCardPayment()));
        this.payWalletButton.setClickListener(() -> this.controller.setPaymentMethod(new WalletPayment()));
        this.payCashOnDeliveryButton.setClickListener(() -> this.controller.setPaymentMethod(new CashOnDeliveryPayment()));
        this.submitButton.setClickListener(this.controller::submitPaymentForm);
        this.fillPaymentForm();
    }

    public void fillPaymentForm() {
        this.paymentFormView.fillForm(this.controller.getPaymentForm());
    }

    public void clickSubmit() {
        this.submitButton.click();
    }

    public void chooseCreditCardPayment() {
        this.payCreditCardButton.click();
    }

    public void chooseWalletPayment() {
        this.payWalletButton.click();
    }

    public void chooseCashOnDeliveryPayment() {
        this.payCashOnDeliveryButton.click();
    }
}
