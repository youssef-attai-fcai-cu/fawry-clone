package ui_utils;

import pay.PaymentFormField;

public class TextFieldView implements PaymentFormField {
    private String text = "";
    public final String placeholder;

    public TextFieldView(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public void fill(String value) {
        this.text = value;

    }

    @Override
    public String getValue() {
        return text;
    }

    @Override
    public String getLabel() {
        return this.placeholder;
    }
}
