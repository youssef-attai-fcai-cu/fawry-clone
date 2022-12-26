package eg.edu.cu.fcai.swe.fawry.service.providers;

import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.payment.request.PaymentRequestBody;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;

public class Vodafone extends ServiceProvider {
    public Vodafone(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean handle(PaymentRequestBody form) {
        Validator.assertFieldExists("mobile", form.fields().get("mobile"));
        Validator.assertFieldExists("zip", form.fields().get("zip"));
        Validator.assertFieldExists("amount", form.billAmount());

        // Use "mobile", "zip" and "amount" with Vodafone's API

        return true;
    }
}
