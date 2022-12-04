package service;

public class VodafoneRecharge implements ServiceProvider{
    @Override
    public boolean handleForm() {
        return false;
    }

    @Override
    public boolean allowsCashOnDelivery() {
        return false;
    }

    @Override
    public float getServiceBillAmount() {
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Vodafone recharge";
    }
}
