package eg.edu.cu.fcai.swe.fawry.discount;

import java.util.List;

public interface DiscountRepository {
    void addOverall(Integer percentage);
    void addSpecific(String providerId, Integer percentage);
    List<Integer> getAllOverall();
    List<Discount> getAllSpecific(String providerId);
}
