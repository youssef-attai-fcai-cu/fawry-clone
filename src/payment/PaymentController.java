package payment;

import service.ServiceProvider;
import transactions.Transaction;
import transactions.TransactionRepository;

import java.util.Map;

public class PaymentController {
    private final ServiceProvider serviceProvider;
    private final TransactionRepository transactionRepository;
    private final int currentUserID;
    private PaymentMethod paymentMethod = new CreditCardPayment();

    public PaymentController(
            ServiceProvider serviceProvider,
            TransactionRepository transactionRepository,
            int currentUserID
    ) {
        this.serviceProvider = serviceProvider;
        this.transactionRepository = transactionRepository;
        this.currentUserID = currentUserID;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void submitPaymentForm(Map<String, String> form) {
        float bill = serviceProvider.getBillAmount(form);
        if (this.serviceProvider.handleForm(form)) {
            if (this.paymentMethod.pay(bill))
                this.transactionRepository.createNew(
                        currentUserID, bill, serviceProvider.getServiceName()
                );
            else
                System.out.println("Your wallet balance is not enough to complete this transaction");
        }

//        TODO: Remove this
        System.out.println();
        for (Transaction t : this.transactionRepository.getAllTransactions()) {
            System.out.println(t);
        }
    }
}
