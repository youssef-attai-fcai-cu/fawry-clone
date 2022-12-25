package eg.edu.cu.fcai.swe.fawry.discount;

import eg.edu.cu.fcai.swe.fawry.auth.InMemoryUserRepository;
import eg.edu.cu.fcai.swe.fawry.auth.UserRepository;
import eg.edu.cu.fcai.swe.fawry.common.Response;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    private final DiscountRepository discountRepository;
    private final UserRepository userRepository;

    @Autowired
    public DiscountController(
            InMemoryDiscountRepository _discountRepository,
            InMemoryUserRepository _userRepository
    ) {
        discountRepository = _discountRepository;
        userRepository = _userRepository;
    }

    @PostMapping
    public Response addNewOverallDiscount(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody DiscountForm form) {
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
    public Response addNewSpecificDiscount(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String providerId, @RequestBody DiscountForm form) {
        Validator.validateAdminToken(userRepository, token);
        Validator.assertFieldExists("percentage", form.percentage());
        discountRepository.addSpecific(providerId, form.percentage());
        return new Response(form.percentage() + "% discount was added to providerId with ID: " + providerId);
    }

    @GetMapping("/{providerId}")
    public List<Integer> getSpecific(@PathVariable String providerId) {
        return discountRepository.getAllSpecific(providerId).stream().map(Discount::percentage).toList();
    }
}
