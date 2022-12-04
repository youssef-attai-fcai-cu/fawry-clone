package ui_utils;

import pay.PaymentFormField;

import java.util.List;

public class DropDownFieldView implements PaymentFormField {
    private final List<String> options;
    private final String label;
    private int selected = 0;

    public DropDownFieldView(List<String> options, String label) {
        this.options = options;
        this.label = label;
    }

    @Override
    public void fill(String value) {
        this.selected = this.options.indexOf(value);
    }

    @Override
    public String getValue() {
        return this.options.get(selected);
    }

    @Override
    public String getLabel() {
        return this.label + " " + this.options ;
    }
}
