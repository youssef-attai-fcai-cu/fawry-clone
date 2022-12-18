package eg.edu.cu.fcai.swe.fawry.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderRepository {
    private final List<ServiceProvider> serviceProviders = List.of(
            new WE("we", "WE")
    );

    public ServiceProvider getById(String providerId) {
        return serviceProviders.stream().filter(serviceProvider -> serviceProvider.getId().equals(providerId)).findFirst().orElse(null);
    }

    public List<ServiceProvider> findByName(String query) {
        return serviceProviders.stream().filter(serviceProvider -> serviceProvider.getName().contains(query.toLowerCase())).toList();
    }
}
