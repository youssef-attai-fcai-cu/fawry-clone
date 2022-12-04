import pay.PaymentForm;
import pay.PaymentFormField;
import pay.ServiceProvider;
import ui_utils.DropDownFieldView;
import ui_utils.TextFieldView;

import java.util.List;

public class VodafoneRecharge implements ServiceProvider {
    PaymentForm paymentForm = new PaymentForm() {
        @Override
        public List<PaymentFormField> getFields() {
            return List.of(
                    new TextFieldView("Mobile number"),
                    new DropDownFieldView(List.of(
                            "10", "20", "30"
                    ), "Amount")
            );
        }
    };

    @Override
    public PaymentForm getPaymentForm() {
        return this.paymentForm;
    }

    @Override
    public boolean handleForm() {
        System.out.println("Successful transaction");
        return true;
    }

    @Override
    public float getServiceBillAmount() {
        return Float.parseFloat(this.paymentForm.getFields().get(1).getValue());
    }

    @Override
    public String getServiceName() {
        return "Vodafone Recharge";
    }
}
