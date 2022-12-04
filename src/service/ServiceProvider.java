package service;

public interface ServiceProvider {
    boolean handleForm();
    boolean allowsCashOnDelivery();
    float getServiceBillAmount();
    String getServiceName();

}
