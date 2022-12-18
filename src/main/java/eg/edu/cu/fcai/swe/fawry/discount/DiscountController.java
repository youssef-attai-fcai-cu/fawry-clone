package eg.edu.cu.fcai.swe.fawry.discount;

import eg.edu.cu.fcai.swe.fawry.common.MissingFieldException;
import eg.edu.cu.fcai.swe.fawry.common.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountController(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @GetMapping
    public List<Discount> getOverall() {
        return discountRepository.getAllOverall();
    }

    @GetMapping("/{providerId}")
    public List<Discount> getSpecific(@PathVariable String providerId) {
        return discountRepository.getAllSpecific(providerId);
    }

    @PostMapping
    public void addNewDiscount(@RequestBody DiscountForm form) {
        if (Objects.isNull(form.percentage()))
            throw new MissingFieldException("percentage");

        if (!Validator.fieldDoesNotExist(form.providerId()))
            discountRepository.addOverall(form.percentage());
        else
            discountRepository.addSpecific(form.providerId(), form.percentage());
    }
}
