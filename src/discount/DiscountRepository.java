package discount;

import java.util.List;

public interface DiscountRepository {
    List<DiscountRecord> getOverallDiscounts();
    List<DiscountRecord> getSpecificDiscounts(String on);
}
