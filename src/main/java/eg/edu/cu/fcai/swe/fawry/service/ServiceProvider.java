package eg.edu.cu.fcai.swe.fawry.service;

import java.util.Map;

public abstract class ServiceProvider {
    private String providerId;
    private String name;

    public ServiceProvider(String providerId, String name) {
        this.providerId = providerId;
        this.name = name;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean handle(Map<String, Object> paymentForm);
}
