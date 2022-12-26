package eg.edu.cu.fcai.swe.fawry.service.controller;

import eg.edu.cu.fcai.swe.fawry.service.ServiceProvider;
import eg.edu.cu.fcai.swe.fawry.service.repository.ServiceProviderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {
    ServiceProviderRepository serviceProviderRepository = new ServiceProviderRepository();

    @GetMapping("/search")
    public List<ServiceProvider> search(@RequestParam String q) {
        return serviceProviderRepository.findByName(q);
    }
}
