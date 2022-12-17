package eg.edu.cu.fcai.swe.fawry.payment;

import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProviderFactory;
import eg.edu.cu.fcai.swe.fawry.transactions.InMemoryTransactionRepository;
import eg.edu.cu.fcai.swe.fawry.transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    ServiceProviderFactory serviceProviderFactory = new ServiceProviderFactory();
    PaymentMethodFactory paymentMethodFactory = new PaymentMethodFactory();
    private final InMemoryTransactionRepository transactionRepository;

    @Autowired
    public PaymentController(InMemoryTransactionRepository repository) {
        transactionRepository = repository;
    }

    @PostMapping("/{providerId}")
    public Transaction pay(@PathVariable String providerId, @RequestBody PaymentForm form) {
        ServiceProvider service = serviceProviderFactory.create(providerId);

        if (Objects.isNull(service))
            throw new InvalidServiceProvider(providerId);

//        Use service provider's API
        if (service.handle(form)) {

//            Handle payment
            PaymentMethod paymentMethod = paymentMethodFactory.create(form.paymentMethod());

            if (!paymentMethod.pay(form.billAmount()))
                throw new PaymentException();

//            Record transaction
            return transactionRepository.createNew(
                    form.currentUserId(),
                    form.billAmount(),
                    service.getName()
            );
        } else
            throw new ServiceProviderException(service);
    }
}
