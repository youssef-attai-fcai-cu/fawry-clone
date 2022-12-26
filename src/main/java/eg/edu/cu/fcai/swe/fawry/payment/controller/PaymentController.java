package eg.edu.cu.fcai.swe.fawry.payment.controller;

import eg.edu.cu.fcai.swe.fawry.auth.repository.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.auth.model.User;
import eg.edu.cu.fcai.swe.fawry.common.repository.UserRepository;
import eg.edu.cu.fcai.swe.fawry.common.*;
import eg.edu.cu.fcai.swe.fawry.common.exception.ResourceNotFound;
import eg.edu.cu.fcai.swe.fawry.discount.model.Discount;
import eg.edu.cu.fcai.swe.fawry.common.repository.DiscountRepository;
import eg.edu.cu.fcai.swe.fawry.discount.repository.InMemoryDiscountRepository;
import eg.edu.cu.fcai.swe.fawry.payment.exception.PaymentException;
import eg.edu.cu.fcai.swe.fawry.payment.exception.ServiceProviderException;
import eg.edu.cu.fcai.swe.fawry.payment.method.PaymentMethod;
import eg.edu.cu.fcai.swe.fawry.payment.method.PaymentMethodFactory;
import eg.edu.cu.fcai.swe.fawry.payment.method.PaymentOption;
import eg.edu.cu.fcai.swe.fawry.payment.request.PaymentRequestBody;
import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;
import eg.edu.cu.fcai.swe.fawry.service.repository.ServiceProviderRepository;
import eg.edu.cu.fcai.swe.fawry.transaction.repository.InMemoryTransactionRepository;
import eg.edu.cu.fcai.swe.fawry.transaction.model.Transaction;
import eg.edu.cu.fcai.swe.fawry.common.repository.TransactionRepository;
import eg.edu.cu.fcai.swe.fawry.wallet.repository.InMemoryWalletRepository;
import eg.edu.cu.fcai.swe.fawry.common.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/pay")
public class PaymentController {
    private final ServiceProviderRepository serviceProviderRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;
    private final WalletRepository walletRepository;
    PaymentMethodFactory paymentMethodFactory;

    @Autowired
    public PaymentController(
            InMemoryTransactionRepository _transactionRepository,
            InMemoryUserRepository _userRepository,
            ServiceProviderRepository _serviceProviderRepository,
            InMemoryDiscountRepository _discountRepository,
            InMemoryWalletRepository _walletRepository
    ) {
        transactionRepository = _transactionRepository;
        userRepository = _userRepository;
        serviceProviderRepository = _serviceProviderRepository;
        discountRepository = _discountRepository;
        walletRepository = _walletRepository;
    }

    @PostMapping("/{providerId}")
    public Transaction pay(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String providerId, @RequestBody PaymentRequestBody form) {
        // Make sure user token exists, if so, get the user associated with this token
        User user = Validator.validateUserToken(userRepository, token);

        paymentMethodFactory = new PaymentMethodFactory(walletRepository, user.userId());

        ServiceProvider service = serviceProviderRepository.getById(providerId);
        if (Objects.isNull(service)) throw new ResourceNotFound("Service provider", providerId);

        // Make sure a non-negative bill amount is provided
        Validator.assertFieldExists("billAmount", form.billAmount());
        Validator.assertNumberWithinRange("billAmount", form.billAmount(), 0.0f, Float.MAX_VALUE);

        // Make sure providerId provider fields are provided
        Validator.assertFieldExists("fields", form.fields());

        float bill = form.billAmount();

        // Apply discounts
        for (int percentage : discountRepository.getAllOverall())
            bill -= bill * (percentage / 100.0f);
        for (Discount discount : discountRepository.getAllSpecific(providerId))
            bill -= bill * (discount.percentage() / 100.0f);

        // Get the selected payment method, if nothing is selected, credit card is selected by default
        PaymentOption paymentOption = form.paymentMethod();
        if (Objects.isNull(paymentOption)) paymentOption = PaymentOption.CREDIT;
        PaymentMethod paymentMethod = paymentMethodFactory.create(paymentOption);

        // The providerId provider does their job
        if (!service.handle(form)) throw new ServiceProviderException(service);

        // Payment
        if (!paymentMethod.pay(bill)) {
            throw new PaymentException();
        }

        // Record a successful payment transaction
        return transactionRepository.create(user.userId(), bill, service.getName());
    }
}
