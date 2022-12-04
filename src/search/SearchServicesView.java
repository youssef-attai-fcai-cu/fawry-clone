package search;

import service.ServiceProvider;

import java.util.List;
import java.util.Scanner;

public class SearchServicesView {

    Scanner scanner = new Scanner(System.in);

    private String searchQuery;
    SearchServicesController controller;


    public SearchServicesView(SearchServicesController controller) {
        this.controller = controller;
    }

    public void inputSearchQuery(String query) {
        this.searchQuery = query;
    }

    public List<ServiceProvider> show() {
        System.out.print("Search: ");
        this.inputSearchQuery(scanner.nextLine());

        return controller.getServices(this.searchQuery);
    }

}
