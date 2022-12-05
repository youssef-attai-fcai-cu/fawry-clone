package discount;

import java.util.List;

public interface DiscountRepository {
    void addNewOverallDiscount(int percentage);
    void addNewSpecificDiscount(int percentage, int serviceID);
    List<DiscountRecord> getOverallDiscounts();

    List<DiscountRecord> getSpecificDiscounts(int serviceID);
}
