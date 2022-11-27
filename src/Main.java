import payment.controller.UserPaymentController;
import payment.view.UserPayForServiceView;

public class Main {
    public static void main(String[] args) {

        UserPayForServiceView view = new UserPayForServiceView();
        UserPaymentController controller = new UserPaymentController(view);
        int amount = 20;
        String paymentMethod = "WalletPayment";

        controller.PerformPayment();




    }
}
