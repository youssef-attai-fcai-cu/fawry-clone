package eg.edu.cu.fcai.swe.fawry.payment;

public class CashOnDeliveryPayment implements PaymentMethod {
    @Override
    public boolean pay(float amount) {
        return true;
    }
}
