package eg.edu.cu.fcai.swe.fawry.payment;

import eg.edu.cu.fcai.swe.fawry.auth.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.auth.UserRepository;
import eg.edu.cu.fcai.swe.fawry.common.MissingFieldException;
import eg.edu.cu.fcai.swe.fawry.common.ResourceNotFound;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProviderFactory;
import eg.edu.cu.fcai.swe.fawry.transaction.InMemoryTransactionRepository;
import eg.edu.cu.fcai.swe.fawry.transaction.Transaction;
import eg.edu.cu.fcai.swe.fawry.transaction.TransactionRepository;
import eg.edu.cu.fcai.swe.fawry.common.InvalidAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    ServiceProviderFactory serviceProviderFactory = new ServiceProviderFactory();
    PaymentMethodFactory paymentMethodFactory = new PaymentMethodFactory();
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public PaymentController(InMemoryTransactionRepository _transactionRepository, InMemoryUserRepository _userRepository) {
        transactionRepository = _transactionRepository;
        userRepository = _userRepository;
    }

    @PostMapping("/{providerId}")
    public Transaction pay(@PathVariable String providerId, @RequestBody PaymentForm form) {
        ServiceProvider service = serviceProviderFactory.create(providerId);

        if (Objects.isNull(service))
            throw new ResourceNotFound("Service provider", providerId);

        if (Validator.fieldDoesNotExist(form.currentUserId()))
            throw new MissingFieldException("currentUserId");

        if (Objects.isNull(userRepository.getById(form.currentUserId())))
            throw new ResourceNotFound("User", form.currentUserId());

        if (Objects.isNull(form.paymentMethod()))
            throw new MissingFieldException("paymentMethod");

        if (Objects.isNull(form.billAmount()))
            throw new MissingFieldException("billAmount");

        if (form.billAmount() <= 0.0f)
            throw new InvalidAmount(form.billAmount());

        if (Objects.isNull(form.fields()))
            throw new MissingFieldException("fields");

        if (service.handle(form)) {
            PaymentMethod paymentMethod = paymentMethodFactory.create(form.paymentMethod());
            if (!paymentMethod.pay(form.billAmount()))
                throw new PaymentException();

            return transactionRepository.create(
                    form.currentUserId(),
                    form.billAmount(),
                    service.getName()
            );
        } else
            throw new ServiceProviderException(service);
    }
}
