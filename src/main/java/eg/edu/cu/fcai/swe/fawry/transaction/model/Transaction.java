package eg.edu.cu.fcai.swe.fawry.transaction.model;

public record Transaction(String transactionId, String userId, Float amount, String providerId) {
}
