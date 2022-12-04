package discount;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDiscountRepository implements DiscountRepository {
    private final List<DiscountRecord> discountRecords = new ArrayList<>();

    public InMemoryDiscountRepository() {
//        this.discountRecords.add(new DiscountRecord(50, "<overall>"));
//        this.discountRecords.add(new DiscountRecord(50, new VodafoneRecharge().getServiceName()));
    }

    @Override
    public List<DiscountRecord> getOverallDiscounts() {
        return discountRecords.stream().filter(discountRecord -> discountRecord.on().equals("<overall>")).toList();
    }

    @Override
    public List<DiscountRecord> getSpecificDiscounts(String on) {
        return discountRecords.stream().filter(discountRecord -> discountRecord.on().equals(on)).toList();
    }

}
