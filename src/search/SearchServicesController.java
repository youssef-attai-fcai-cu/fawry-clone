package search;


import service.ServiceProvider;

import java.util.List;


public class SearchServicesController {

    List<ServiceProvider> serviceProviders;

    public SearchServicesController(List<ServiceProvider> serviceProvider) {
        this.serviceProviders = serviceProvider;
    }

    public List<ServiceProvider> getServices(String query) {
        return this.serviceProviders
                .stream().filter(serviceProvider -> serviceProvider
                        .getServiceName()
                        .toLowerCase()
                        .contains(query.toLowerCase()))
                .toList();
    }
}
