package pay;

public interface ServiceProvider {
    PaymentForm getPaymentForm();

    boolean handleForm();
    float getServiceBillAmount();

    String getServiceName();
}
