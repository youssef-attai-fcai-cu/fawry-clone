package eg.edu.cu.fcai.swe.fawry.service;

import eg.edu.cu.fcai.swe.fawry.payment.PaymentForm;

public abstract class ServiceProvider {
    private String id;
    private String name;

    public ServiceProvider(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean handle(PaymentForm form);
}
