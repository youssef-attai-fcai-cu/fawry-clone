package eg.edu.cu.fcai.swe.fawry.transaction;

public record Transaction(String transactionId, String userId, Float amount, String service) {
}
