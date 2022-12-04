import pay.*;

public class Main {
    public static void main(String[] args) {
        ServiceProvider serviceProvider = new VodafoneRecharge();
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        PaymentController paymentController = new PaymentController(serviceProvider, transactionRepository, 69);
        PaymentView paymentView = new PaymentView(paymentController);
        paymentView.chooseWalletPayment();
        paymentView.clickSubmit();
        for (Transaction t : transactionRepository.getAllTransactions()) {
            System.out.println(t.toString());
        }
    }
}
