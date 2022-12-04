package search.controller;


import search.ServiceProvider;

import java.util.ArrayList;
import java.util.List;


public class SearchServicesController{

    List<ServiceProvider> serviceProvider = new ArrayList<>();

    public SearchServicesController(List<ServiceProvider> serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public SearchServicesController() {
    }

    public List<ServiceProvider> getServices(String query) {
                return(this.serviceProvider);


    }
}
