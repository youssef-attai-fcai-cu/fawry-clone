package pay;

public interface ServiceProvider {

    boolean handleForm();
    float getServiceBillAmount();

    String getServiceName();
}
