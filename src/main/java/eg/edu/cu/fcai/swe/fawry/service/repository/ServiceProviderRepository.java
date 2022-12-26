package eg.edu.cu.fcai.swe.fawry.service.repository;

import eg.edu.cu.fcai.swe.fawry.service.providers.Etisalat;
import eg.edu.cu.fcai.swe.fawry.service.providers.Vodafone;
import eg.edu.cu.fcai.swe.fawry.service.providers.WE;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderRepository {
    private final List<ServiceProvider> serviceProviders = List.of(
            new WE("we", "WE"),
            new Vodafone("vodafone", "Vodafone"),
            new Etisalat("etisalat", "Etisalat")
    );

    public ServiceProvider getById(String providerId) {
        return serviceProviders.stream().filter(serviceProvider -> serviceProvider.getId().equals(providerId)).findFirst().orElse(null);
    }

    public List<ServiceProvider> findByName(String query) {
        return serviceProviders.stream().filter(serviceProvider -> serviceProvider.getName().toLowerCase().contains(query.toLowerCase())).toList();
    }
}
