package eg.edu.cu.fcai.swe.fawry.service.providers;

import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.payment.request.PaymentRequestBody;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;

public class WE extends ServiceProvider {
    public WE(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean handle(PaymentRequestBody form) {
        Validator.assertFieldExists("mobile", form.fields().get("mobile"));
        Validator.assertFieldExists("amount", form.billAmount());

        // Use "mobile" and "amount" with WE's API

        return true;
    }
}
