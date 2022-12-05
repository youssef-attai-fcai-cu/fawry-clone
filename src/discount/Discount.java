package discount;

import service.PaymentFormField;
import service.ServiceProvider;

import java.util.List;
import java.util.Map;

public class Discount implements ServiceProvider {
    private ServiceProvider wrapped;
    private int percentage;

    public Discount(ServiceProvider serviceProvider, int percentage) {
        this.wrapped = serviceProvider;
        this.percentage = percentage;
    }

    @Override
    public List<PaymentFormField> getPaymentFormFields() {
        return this.wrapped.getPaymentFormFields();
    }

    @Override
    public boolean handleForm(Map<String, String> form) {
        return this.wrapped.handleForm(form);
    }

    @Override
    public boolean allowsCashOnDelivery() {
        return this.wrapped.allowsCashOnDelivery();
    }

    @Override
    public String getServiceName() {
        return this.wrapped.getServiceName() + " (%" + this.percentage + " OFF)";
    }

    @Override
    public float getBillAmount(Map<String, String> form) {
        float bill = this.wrapped.getBillAmount(form);
        return bill - (bill * (percentage / 100.0f));
    }

    @Override
    public int getServiceID() {
        return wrapped.getServiceID();
    }
}
