package pay;

import payment.CreditCardPayment;
import payment.PaymentMethod;

public class PaymentController {
    private final ServiceProvider serviceProvider;
    private final TransactionRepository transactionRepository;
    private int currentUserID;
    private PaymentMethod paymentMethod = new CreditCardPayment();

    public PaymentController(ServiceProvider serviceProvider, TransactionRepository transactionRepository, int currentUserID) {
        this.serviceProvider = serviceProvider;
        this.transactionRepository = transactionRepository;
        this.currentUserID = currentUserID;
    }

    public PaymentForm getPaymentForm() {
        return serviceProvider.getPaymentForm();
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void submitPaymentForm() {
        if (this.serviceProvider.handleForm()) {
            float billAmount = serviceProvider.getServiceBillAmount();
            this.paymentMethod.pay(billAmount);
            this.transactionRepository.createNew(
                    currentUserID, billAmount, serviceProvider.getServiceName()
            );
        }
        ;
    }
}
