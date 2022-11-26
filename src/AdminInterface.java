import service.Discount;

import java.util.List;

public interface AdminInterface {
    List<RefundRequest> getAllRefunds();
    void acceptRefundRequest(RefundRequest refundRequest);
    void rejectRefundRequest(RefundRequest refundRequest);
    void addServiceProvider(ServiceProvider serviceProvider);
    void addDiscount(Discount discount);
}
