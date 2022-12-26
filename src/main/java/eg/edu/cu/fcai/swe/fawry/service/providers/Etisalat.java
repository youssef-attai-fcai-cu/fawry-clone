package eg.edu.cu.fcai.swe.fawry.service.providers;

import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.payment.request.PaymentRequestBody;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;

public class Etisalat extends ServiceProvider {
    public Etisalat(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean handle(PaymentRequestBody form) {
        Validator.assertFieldExists("phone", form.fields().get("phone"));
        Validator.assertFieldExists("address", form.fields().get("address"));
        Validator.assertFieldExists("amount", form.billAmount());

        // Use "phone", "address" and "amount" with Vodafone's API

        return true;
    }
}
