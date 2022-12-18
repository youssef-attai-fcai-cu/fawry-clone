package eg.edu.cu.fcai.swe.fawry.discount;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InMemoryDiscountRepository implements DiscountRepository {
    private final List<Discount> discounts = new ArrayList<>();

    @Override
    public void addOverall(Integer percentage) {
        addSpecific("*", percentage);
    }

    @Override
    public void addSpecific(String providerId, Integer percentage) {
        discounts.add(new Discount(providerId, percentage));
    }

    @Override
    public List<Discount> getAllOverall() {
        return getAllSpecific("*");
    }

    @Override
    public List<Discount> getAllSpecific(String providerId) {
        return discounts.stream().filter(discount -> discount.providerId().equals(providerId)).toList();
    }
}
