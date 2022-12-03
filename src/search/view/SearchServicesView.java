package search.view;


import search.ServiceProvider;
import search.controller.SearchServicesController;



import java.util.List;
import java.util.Scanner;

public class SearchServicesView {

    Scanner scanner = new Scanner(System.in);

    private String searchQuery;
    private List<ServiceProvider> searchResults;
    SearchServicesController controller;


    public SearchServicesView(SearchServicesController controller) {

        this.controller = controller;
    }

    public void setSearchQuery()
    {
        this.searchQuery = scanner.nextLine();
    }
    public String getSearchQuery() {
        return searchQuery;
    }


    public void searchQuery()
    {
        setSearchQuery();
        searchResults = controller.getServices(getSearchQuery());
    }






}
