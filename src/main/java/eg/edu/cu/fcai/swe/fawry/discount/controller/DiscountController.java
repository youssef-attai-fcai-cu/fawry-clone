package eg.edu.cu.fcai.swe.fawry.discount.controller;

import eg.edu.cu.fcai.swe.fawry.auth.repository.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.common.exception.ResourceNotFound;
import eg.edu.cu.fcai.swe.fawry.common.repository.UserRepository;
import eg.edu.cu.fcai.swe.fawry.common.Response;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import eg.edu.cu.fcai.swe.fawry.common.repository.DiscountRepository;
import eg.edu.cu.fcai.swe.fawry.discount.repository.InMemoryDiscountRepository;
import eg.edu.cu.fcai.swe.fawry.discount.model.Discount;
import eg.edu.cu.fcai.swe.fawry.discount.request.AddDiscountRequestBody;
import eg.edu.cu.fcai.swe.fawry.provider.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    private final DiscountRepository discountRepository;
    private final UserRepository userRepository;
    private final ServiceProviderRepository serviceProviderRepository;


    @Autowired
    public DiscountController(
            InMemoryDiscountRepository _discountRepository,
            InMemoryUserRepository _userRepository,
            ServiceProviderRepository _serviceProviderRepository
    ) {
        discountRepository = _discountRepository;
        userRepository = _userRepository;
        serviceProviderRepository = _serviceProviderRepository;
    }

    @PostMapping
    public Response addNewOverallDiscount(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody AddDiscountRequestBody form) {
        Validator.validateAdminToken(userRepository, token);
        Validator.assertFieldExists("percentage", form.percentage());
        discountRepository.addOverall(form.percentage());
        return new Response(form.percentage() + "% discount was added to all services");
    }

    @GetMapping
    public List<Integer> getOverall() {
        return discountRepository.getAllOverall();
    }

    @PostMapping("/{providerId}")
    public Response addNewSpecificDiscount(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String providerId, @RequestBody AddDiscountRequestBody form) {
        Validator.validateAdminToken(userRepository, token);
        Validator.assertFieldExists("percentage", form.percentage());
        if (Objects.isNull(serviceProviderRepository.getById(providerId)))
            throw new ResourceNotFound("service provider", providerId);
        discountRepository.addSpecific(providerId, form.percentage());
        return new Response(form.percentage() + "% discount was added to providerId with ID: " + providerId);
    }

    @GetMapping("/{providerId}")
    public List<Integer> getSpecific(@PathVariable String providerId) {
        return discountRepository.getAllSpecific(providerId).stream().map(Discount::percentage).toList();
    }
}
