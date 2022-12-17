package eg.edu.cu.fcai.swe.fawry.service;

import java.util.List;

public class ServiceProviderFactory {
    List<ServiceProvider> serviceProviders = List.of(
        new WE("we", "WE")
    );

    public ServiceProvider create(String providerId) {
        for (ServiceProvider provider : serviceProviders)
            if (provider.getId().equals(providerId))
                return provider;
        return null;
    }
}
