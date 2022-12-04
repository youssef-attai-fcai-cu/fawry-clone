package service;

import java.util.List;
import java.util.Map;

public class We implements ServiceProvider {
    PaymentFormField mobileNumber = new TextField("Mobile number");
    PaymentFormField amount = new DropDownField("Amount", List.of(10, 20, 30));

    @Override
    public List<PaymentFormField> getPaymentFormFields() {
        return List.of(mobileNumber, amount);
    }

    @Override
    public boolean handleForm(Map<String, String> form) {
        form.get(mobileNumber.label());
        form.get(amount.label());
        return true;
    }

    @Override
    public boolean allowsCashOnDelivery() {
        return true;
    }

    @Override
    public String getServiceName() {
        return "We";
    }

    @Override
    public float getBillAmount(Map<String, String> form) {
        return Float.parseFloat(form.get(amount.label()));
    }
}
