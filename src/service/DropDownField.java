package service;

import java.util.List;

public record DropDownField(String label, List<Object> options) implements PaymentFormField {
    @Override
    public String display() {
        return this.label + " " + options.toString() + ": ";
    }

}
