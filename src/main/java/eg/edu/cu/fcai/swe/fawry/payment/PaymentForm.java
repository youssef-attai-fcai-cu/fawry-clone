package eg.edu.cu.fcai.swe.fawry.payment;

import java.util.Map;

public record PaymentForm(
        String currentUserId,
        PaymentOption paymentMethod,
        Float billAmount,
        Map<String, Object> fields
) {
}
