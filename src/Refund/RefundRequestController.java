package Refund;

public class RefundRequestController {
    private final RefundRepository refundRepository;

    public RefundRequestController(RefundRepository refundRepository) {
        this.refundRepository = refundRepository;
    }
}
