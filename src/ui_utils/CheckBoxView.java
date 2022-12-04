package ui_utils;

public class CheckBoxView {
    private boolean toggled = false;

    public void toggle() {
        this.toggled = !toggled;
    }

    public boolean isToggled() {
        return toggled;
    }
}
