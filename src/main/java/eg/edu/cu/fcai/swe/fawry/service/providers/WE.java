package eg.edu.cu.fcai.swe.fawry.service.providers;

import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;

import java.util.Map;

public class WE extends ServiceProvider {
    public WE(String id, String name) {
        super(id, name);
    }

    @Override
    public boolean handle(Map<String, Object> paymentForm) {
        Validator.assertFieldExists("mobile", paymentForm.get("mobile"));
        Validator.assertFieldExists("amount", paymentForm.get("billAmount"));

        // Use "mobile" and "amount" with WE's API

        return true;
    }
}
