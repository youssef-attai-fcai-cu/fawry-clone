package discount;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDiscountRepository implements DiscountRepository {
    private final List<DiscountRecord> discountRecords = new ArrayList<>();

    @Override
    public void addNewOverallDiscount(int percentage) {
        this.discountRecords.add(new DiscountRecord(percentage, 0));
    }

    @Override
    public void addNewSpecificDiscount(int percentage, int serviceID) {
        this.discountRecords.add(new DiscountRecord(percentage, serviceID));
    }

    @Override
    public List<DiscountRecord> getOverallDiscounts() {
        return discountRecords.stream().filter(discountRecord -> discountRecord.serviceID() == 0).toList();
    }

    @Override
    public List<DiscountRecord> getSpecificDiscounts(int serviceID) {
        return discountRecords.stream().filter(discountRecord -> discountRecord.serviceID() == serviceID).toList();
    }

}
