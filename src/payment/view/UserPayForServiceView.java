package payment.view;

import payment.PaymentMethod;

import java.util.Scanner;

public class UserPayForServiceView {

    Scanner scanner = new Scanner(System.in);

    private String paymentMethod;
    private int paymentAmount;



    public void setPaymentMethod()
    {
        this.paymentMethod = scanner.nextLine();
    }

    public String getPaymentMethod(){
        return paymentMethod;
    }


    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount() {
        this.paymentAmount = scanner.nextInt();
    }
}
