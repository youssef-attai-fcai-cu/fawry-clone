package service;

import java.util.List;
import java.util.Map;

public interface ServiceProvider {
    List<PaymentFormField> getPaymentFormFields();

    boolean handleForm(Map<String, String> form);

    boolean allowsCashOnDelivery();

    String getServiceName();

    float getBillAmount(Map<String, String> form);

    int getServiceID();
}
