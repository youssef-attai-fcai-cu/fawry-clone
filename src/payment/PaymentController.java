package payment;

import auth.User;
import service.ServiceProvider;
import transactions.TransactionRepository;

import java.util.Map;

public class PaymentController {
    public final ServiceProvider serviceProvider;
    private final TransactionRepository transactionRepository;
    public final User currentUser;
    private PaymentMethod paymentMethod = new CreditCardPayment();

    public PaymentController(
            ServiceProvider service,
            TransactionRepository repository,
            User user
    ) {
        serviceProvider = service;
        transactionRepository = repository;
        currentUser = user;
    }

    public void setPaymentMethod(PaymentMethod method) {
        paymentMethod = method;
    }

    public boolean submitPaymentForm(Map<String, String> form) {
        float bill = serviceProvider.getBillAmount(form);

        if (serviceProvider.handleForm(form)) {
            if (paymentMethod.pay(bill))
                transactionRepository.createNew(currentUser.id(), bill, serviceProvider.getServiceName());
            else return false;
        }
        return true;
    }
}
