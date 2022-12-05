package discount;

import service.ServiceProvider;

import java.util.List;

public class DiscountController {
    public final DiscountRepository discountRepository;
    public final List<ServiceProvider> allServices;

    public DiscountController(DiscountRepository repository, List<ServiceProvider> services) {
        allServices = services;
        discountRepository = repository;
    }

    public void addNewOverallDiscount(int percentage) {
        discountRepository.addNewOverallDiscount(percentage);
    }

    public void addNewSpecificDiscount(int percentage, int choice) {
        discountRepository.addNewSpecificDiscount(percentage, allServices.get(choice - 1).getServiceID());
    }
}
