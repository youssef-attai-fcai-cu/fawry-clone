package payment.controller;

import payment.CashOnDeliveryPayment;
import payment.CreditCardPayment;
import payment.PaymentMethod;
import payment.WalletPayment;
import payment.view.UserPayForServiceView;

public class UserPaymentController {
    PaymentMethod strategy;
    private final UserPayForServiceView view;


    public UserPaymentController(UserPayForServiceView view) {
        this.view = view;
    }



    private void setStrategy(PaymentMethod strategy) {
        this.strategy = strategy;
    }

    private void executeStrategy(int amount) {

        strategy.pay(amount);
    }

    private PaymentMethod pickPaymentStrategy(String paymentMethod)
    {

        return switch(paymentMethod){
            case "CashOnDeliveryPayment" -> new CashOnDeliveryPayment();
            case "WalletPayment" -> new WalletPayment();
            default -> new CreditCardPayment();
        };


    }

    private void getPaymentType(){
        setStrategy(pickPaymentStrategy(this.view.getPaymentMethod()));
    }

    public void PerformPayment()
    {
        this.view.setPaymentMethod();
        this.view.setPaymentAmount();
        getPaymentType();
        executeStrategy(this.view.getPaymentAmount());
    }

    }





