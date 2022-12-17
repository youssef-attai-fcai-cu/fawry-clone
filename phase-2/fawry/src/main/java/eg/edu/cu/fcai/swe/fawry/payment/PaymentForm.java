package eg.edu.cu.fcai.swe.fawry.payment;

import java.util.Map;

public record PaymentForm(
        Integer currentUserId,
        Integer paymentMethod,
        Float billAmount,
        Map<String, Object> fields
) {
}
