package payment;

import discount.Discount;
import discount.DiscountRecord;
import discount.DiscountRepository;
import service.ServiceProvider;
import transactions.TransactionRepository;

public class PaymentController {
    private ServiceProvider serviceProvider;
    private final TransactionRepository transactionRepository;
    private final DiscountRepository discountRepository;
    private final int currentUserID;
    private PaymentMethod paymentMethod = new CreditCardPayment();

    public PaymentController(
            ServiceProvider serviceProvider,
            TransactionRepository transactionRepository,
            DiscountRepository discountRepository,
            int currentUserID
    ) {
        this.serviceProvider = serviceProvider;
        this.transactionRepository = transactionRepository;
        this.discountRepository = discountRepository;
        this.currentUserID = currentUserID;
    }

    public ServiceProvider getServiceProvider() {
        return this.serviceProvider;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void submitPaymentForm() {
//        TODO: Revise
        ServiceProvider s = serviceProvider;
        for (DiscountRecord d : discountRepository.getOverallDiscounts()) {
            s = new Discount(s, d.percentage());
        }
        for (DiscountRecord d : discountRepository.getSpecificDiscounts(serviceProvider.getServiceName())) {
            s = new Discount(s, d.percentage());
        }
        this.serviceProvider = s;
        if (this.serviceProvider.handleForm()) {
            float billAmount = this.serviceProvider.getServiceBillAmount();
            this.paymentMethod.pay(billAmount);
            this.transactionRepository.createNew(
                    currentUserID, billAmount, serviceProvider.getServiceName()
            );
        }
    }
}
