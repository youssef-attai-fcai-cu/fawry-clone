import auth.*;
import discount.*;
import menu.MenuController;
import menu.UserMenuView;
import payment.*;
import refund.*;
import search.SearchServicesController;
import search.SearchServicesView;
import service.*;
import transactions.InMemoryTransactionRepository;
import transactions.TransactionRepository;
import wallet.WalletController;
import wallet.WalletView;

import java.util.*;

public class Fawry {
    private User currentUser = null; // Shared user object that holds the currently signed-in user data
    private final TransactionRepository transactionRepository = new InMemoryTransactionRepository();
    private final DiscountRepository discountRepository = new InMemoryDiscountRepository();
    private final UserRepository userRepository = new InMemoryUserRepository();
    private final RefundRepository refundRepository = new InMemoryRefundRepository();

    public static void main(String[] args) {
        Fawry fawry = new Fawry();

        while (fawry.currentUser == null) {
            fawry.auth();
            if (!fawry.currentUser.isAdmin()) {
                fawry.userMenu();
            } else {
                fawry.adminMenu();
            }
        }
    }

    private void adminMenu() {
        while (this.currentUser != null) {
            System.out.println("""

                    1. Manage discounts
                    2. Manage refund requests
                    3. Logout""");
            int choice = inputChoice(1, 3);
            switch (choice) {
                case 1 -> this.manageDiscounts();
                case 2 -> this.manageRefundRequests();
                case 3 -> this.logout();
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void manageRefundRequests() {
        RefundResponseController refundResponseController = new RefundResponseController(refundRepository);
        RefundResponseView refundResponseView = new RefundResponseView(refundResponseController);
        refundResponseView.show();
    }

    private void manageDiscounts() {
        DiscountController discountController = new DiscountController(discountRepository, getAllServices());
        DiscountView discountView = new DiscountView(discountController);
        discountView.show();
    }

    public void auth() {
        UserAuthController userAuthController = new UserAuthController(userRepository);
        UserAuthView userAuthView = new UserAuthView(userAuthController);
        this.currentUser = userAuthView.show();
        if (this.currentUser == null) auth();
    }

    public void userMenu() {
        MenuController menuController = new MenuController();
        UserMenuView userMenuView = new UserMenuView(menuController);
        while (this.currentUser != null) userMenuView.show();
    }

    private void myRequestRequests() {
        RefundRequestController refundRequestController = new RefundRequestController(refundRepository, transactionRepository, currentUser);
        RefundRequestView refundRequestView = new RefundRequestView(refundRequestController);
        refundRequestView.listRefundRequests();
    }

    private void addFundsToWallet() {
        WalletController walletController = new WalletController(userRepository, currentUser);
        WalletView walletView = new WalletView(walletController);
        walletView.show();
    }

    private void requestRefund() {
        RefundRequestController refundRequestController = new RefundRequestController(refundRepository, transactionRepository, currentUser);
        RefundRequestView refundRequestView = new RefundRequestView(refundRequestController);
        int transactionID = refundRequestView.selectTransaction();
        if (transactionID == -1) return;
        refundRequestView.createRefundRequest(transactionID);
    }

    private void searchForServices() {
        SearchServicesController searchServicesController = new SearchServicesController(getAllServices());
        SearchServicesView searchServicesView = new SearchServicesView(searchServicesController);
        ServiceProvider serviceProvider = searchServicesView.search();
        if (serviceProvider == null) searchForServices();
        else payForService(serviceProvider);
    }

    private List<ServiceProvider> getAllServices() {
        ServiceProvider[] services = {
                new VodafoneRecharge(),
                new We()
        };

//        For each service
        for (int i = 0; i < services.length; i++) {
//            For each overall service
            for (DiscountRecord d : discountRepository.getSpecificDiscounts(services[i].getServiceID())) {
//                Wrap service with discount decorator
                services[i] = new Discount(services[i], d.percentage());
            }
//            For each specific service
            for (DiscountRecord d : discountRepository.getOverallDiscounts()) {
//                Wrap service with discount decorator
                services[i] = new Discount(services[i], d.percentage());
            }
        }
        return Arrays.stream(services).toList();
    }

    private void payForService(ServiceProvider serviceProvider) {
        PaymentController paymentController = new PaymentController(serviceProvider, transactionRepository, currentUser);
        PaymentView paymentView = new PaymentView(paymentController);

        paymentView.selectPaymentMethod();
        Map<String, String> form = new HashMap<>();

        for (PaymentFormField field : serviceProvider.getPaymentFormFields()) {
            String fieldValue = paymentView.inputField(field);
            form.put(field.label(), fieldValue);
        }

        paymentView.submitForm(form);
    }

    private void logout() {
        this.currentUser = null;
    }
}
