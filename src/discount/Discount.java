package discount;

import service.ServiceProvider;

public class Discount implements ServiceProvider {

    private ServiceProvider service;
    private int percentage;

    public Discount(ServiceProvider service, int percentage) {
        this.service = service;
        this.percentage = percentage;
    }

    @Override
    public boolean handleForm() {
        return service.handleForm();
    }

    @Override
    public boolean allowsCashOnDelivery() {
        return service.allowsCashOnDelivery();
    }

    @Override
    public float getServiceBillAmount() {
        float b = service.getServiceBillAmount();
        return b - (b * ((float) this.percentage / (float) 100));
    }

    @Override
    public String getServiceName() {
        return service.getServiceName();
    }
}
