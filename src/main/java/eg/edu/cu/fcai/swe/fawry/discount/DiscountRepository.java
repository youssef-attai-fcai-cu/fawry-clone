package eg.edu.cu.fcai.swe.fawry.discount;

import java.util.List;

public interface DiscountRepository {
    void addOverall(Integer percentage);
    void addSpecific(String providerId, Integer percentage);
    List<Discount> getAllOverall();
    List<Discount> getAllSpecific(String providerId);
}
