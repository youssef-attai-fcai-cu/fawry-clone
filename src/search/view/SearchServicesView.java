package search.view;


import search.controller.SearchServicesController;




import java.util.Scanner;

public class SearchServicesView {

    Scanner scanner = new Scanner(System.in);

    private String searchQuery;
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
        controller.handle(getSearchQuery());
    }






}
