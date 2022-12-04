package pay;

import java.util.Scanner;

public class PaymentFormView {
    private final Scanner scanner = new Scanner(System.in);

    public boolean fillForm(PaymentForm paymentForm) {
        for (PaymentFormField field : paymentForm.getFields()) {
            System.out.print(field.getLabel() + ": ");
            field.fill(scanner.nextLine());
        }
//        Filled successfully
        return true;
    }
}
