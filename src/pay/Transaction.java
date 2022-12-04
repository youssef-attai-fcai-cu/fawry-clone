package pay;

public record Transaction(int userID, int transactionID, float amount, String service) {
}
