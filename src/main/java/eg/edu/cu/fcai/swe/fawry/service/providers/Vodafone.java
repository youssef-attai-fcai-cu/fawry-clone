package eg.edu.cu.fcai.swe.fawry.service.providers;

import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;

import java.util.Map;

public class Vodafone extends ServiceProvider {
    public Vodafone(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean handle(Map<String, Object> paymentForm) {
        Validator.assertFieldExists("mobile", paymentForm.get("mobile"));
        Validator.assertFieldExists("zip", paymentForm.get("zip"));
        Validator.assertFieldExists("amount", paymentForm.get("billAmount"));

        // Use "mobile", "zip" and "amount" with Vodafone's API

        return true;
    }
}
