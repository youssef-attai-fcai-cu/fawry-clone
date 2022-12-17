package eg.edu.cu.fcai.swe.fawry.transactions;

public record Transaction(int userID, int transactionID, float amount, String service) {
}
