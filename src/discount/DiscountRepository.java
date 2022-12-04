package discount;

import java.util.List;

public interface DiscountRepository {
    void addNewSpecificDiscount(int percentage, String serviceName);
    List<DiscountRecord> getOverallDiscounts();
    List<DiscountRecord> getSpecificDiscounts(String on);
}
