package search;

import core.View;
import service.ServiceProvider;

import java.util.List;

public class SearchServicesView extends View {
    private final SearchServicesController searchServicesController;

    public SearchServicesView(SearchServicesController controller) {
        searchServicesController = controller;
    }

    public ServiceProvider search() {
        List<ServiceProvider> results = searchServicesController.findServices(inputString("Search: "));
        if (results.size() > 0) {
            System.out.println("Found " + results.size() + " result(s)");
            for (int i = 0; i < results.size(); i++)
                System.out.println((i + 1) + ". " + results.get(i).getServiceName());
            System.out.println(0 + ". Search for something else");
            int choice = inputWithinRange("Select a service to pay for: ", 0, results.size());
            if (choice == 0) return search();
            return results.get(choice - 1);
        } else System.out.println("No results found");
        return null;
    }
}
