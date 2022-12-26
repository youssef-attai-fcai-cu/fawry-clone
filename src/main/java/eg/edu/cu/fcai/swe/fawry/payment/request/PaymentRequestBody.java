package eg.edu.cu.fcai.swe.fawry.payment.request;

import eg.edu.cu.fcai.swe.fawry.payment.method.PaymentOption;

import java.util.Map;

public record PaymentRequestBody(
        PaymentOption paymentMethod,
        Float billAmount,
        Map<String, Object> fields
) {
}
