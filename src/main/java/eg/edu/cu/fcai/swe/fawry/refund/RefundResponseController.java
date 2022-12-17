package eg.edu.cu.fcai.swe.fawry.refund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/refundResponse")
public class RefundResponseController {
    private final RefundRepository refundRepository;

    @Autowired
    public RefundResponseController(RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
    }

    @GetMapping
    public List<RefundRequest> getAllRefundRequests() {
        return refundRepository.getAllRefundRequests();
    }

    @PutMapping
    public RefundStatus setRefundRequestStatus(RefundResponseForm form) {
//        TODO: Allow admin to set the refund request status
        refundRepository.setRefundRequestStatus(form.requestID(), form.refundStatus());
        return form.refundStatus();
    }
}
