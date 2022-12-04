package service;

public record TextField(String label) implements PaymentFormField {
    @Override
    public String display() {
        return this.label + ": ";
    }
}
