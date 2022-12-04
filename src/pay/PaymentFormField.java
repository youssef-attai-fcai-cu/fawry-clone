package pay;

public interface PaymentFormField {
    void fill(String value);
    String getValue();

    String getLabel();
}
