package eg.edu.cu.fcai.swe.fawry.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {
    ServiceProviderRepository serviceProviderRepository = new ServiceProviderRepository();

    @GetMapping
    public List<ServiceProvider> search(@RequestParam String q) {
        return serviceProviderRepository.findByName(q);
    }
}
