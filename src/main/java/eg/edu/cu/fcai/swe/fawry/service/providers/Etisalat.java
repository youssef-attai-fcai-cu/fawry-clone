package eg.edu.cu.fcai.swe.fawry.service.providers;

import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;

import java.util.Map;

public class Etisalat extends ServiceProvider {
    public Etisalat(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean handle(Map<String, Object> paymentForm) {
        Validator.assertFieldExists("phone", paymentForm.get("phone"));
        Validator.assertFieldExists("address", paymentForm.get("address"));
        Validator.assertFieldExists("billAmount", paymentForm.get("billAmount"));

        // Use "phone", "address" and "amount" with Vodafone's API

        return true;
    }
}
