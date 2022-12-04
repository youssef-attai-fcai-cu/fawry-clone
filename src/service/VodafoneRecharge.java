package service;

import java.util.List;
import java.util.Map;

public class VodafoneRecharge implements ServiceProvider {
    private final PaymentFormField mobileNumber = new TextField("Mobile number");
    private final PaymentFormField amount = new TextField("Amount");

    @Override
    public List<PaymentFormField> getPaymentFormFields() {
        return List.of(mobileNumber, amount);
    }

    @Override
    public boolean handleForm(Map<String, String> form) {
        String mobileNumber = form.get("Mobile number");
        float amount = Float.parseFloat(form.get("Amount"));
//        Use form fields to make the proper API request
        return true;
    }

    @Override
    public boolean allowsCashOnDelivery() {
        return false;
    }

    @Override
    public String getServiceName() {
        return "Vodafone recharge";
    }

    @Override
    public float getBillAmount(Map<String, String> form) {
        return Float.parseFloat(form.get("Amount"));
    }
}
