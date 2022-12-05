import auth.*;
import discount.Discount;
import discount.DiscountRecord;
import discount.DiscountRepository;
import discount.InMemoryDiscountRepository;
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
    private User currentUser = null;
    private final Scanner scanner = new Scanner(System.in);
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

        if (refundRepository.count() > 0) {
            System.out.println("Select a refund request by its ID\n");
            refundResponseView.show();
            int requestID = inputChoice(0, refundRepository.count());

            System.out.println("""
                    Request state:

                    1. Accept
                    2. Reject
                    3. Keep pending"""
            );
            RefundStatus refundStatus;
            switch (inputChoice(0, refundRepository.count())) {
                case 1 -> refundStatus = RefundStatus.Accepted;
                case 2 -> refundStatus = RefundStatus.Rejected;
                default -> refundStatus = RefundStatus.Pending;
            }

            refundResponseView.setRefundStatus(requestID, refundStatus);
        } else {
            System.out.println("\nThere are no requested refunds\n");
        }
    }

    private void manageDiscounts() {
        System.out.println("\nAdd a discount on:\n");
        for (int i = 0; i < getAllServices().size(); i++) {
            System.out.println((i + 1) + ". " + getAllServices().get(i).getServiceName());
        }
        System.out.println("0. All services");
        int choice = inputChoice(0, getAllServices().size());

        System.out.println("\nDiscount percentage:");
        int percentage = inputChoice(1, 99);

        if (choice == 0) {
            discountRepository.addNewOverallDiscount(percentage);
        } else {
            discountRepository.addNewSpecificDiscount(percentage, getAllServices().get(choice - 1).getServiceID());
        }
    }

    public void auth() {
        UserAuthController userAuthController = new UserAuthController(userRepository);
        UserSignInView userSignInView = new UserSignInView(userAuthController);
        UserSignUpView userSignUpView = new UserSignUpView(userAuthController);

        System.out.println("Fawry\n");
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");

        int choice = this.inputChoice(1, 2);

        if (choice == 1) {
            this.currentUser = userSignInView.show();
            if (this.currentUser == null) System.out.println("\nUser not found\n");
        } else if (choice == 2) {
            this.currentUser = userSignUpView.show();
            if (this.currentUser == null) System.out.println("\nUser with this username or email already exists\n");
        }

        if (this.currentUser == null) auth();
    }

    public void userMenu() {
        while (this.currentUser != null) {
            System.out.println("""

                    1. Search for services
                    2. Make a refund request
                    3. See my refund requests
                    4. Add funds to wallet
                    5. Logout""");
            int choice = inputChoice(1, 5);
            switch (choice) {
                case 1 -> this.searchForServices();
                case 2 -> this.requestRefund();
                case 3 -> this.myRequestRequests();
                case 4 -> this.addFundsToWallet();
                case 5 -> this.logout();
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void myRequestRequests() {
        if (refundRepository.count() > 0) {
            for (RefundRequest r : refundRepository.getRefundRequestsByUserID(currentUser.id())) {
                System.out.println(r.toString());
            }
        } else {
            System.out.println("\nYou have not requested any refunds\n");
        }
    }

    private void addFundsToWallet() {
        WalletController walletController = new WalletController(userRepository, currentUser.id());
        WalletView walletView = new WalletView(walletController);
        System.out.println("Your wallet balance: " + currentUser.walletBalance());
        System.out.print("Amount: ");

        float funds = -1;

        while (funds == -1) {
            try {
                funds = Float.parseFloat(this.scanner.nextLine());
            } catch (Exception e) {
                funds = -1;
            }
        }

        walletView.addFunds(funds);
    }

    private void requestRefund() {
        RefundRequestController refundRequestController = new RefundRequestController(refundRepository, transactionRepository, currentUser.id());
        RefundRequestView refundRequestView = new RefundRequestView(refundRequestController);

        if (refundRequestView.listTransactions(transactionRepository.getTransactionsByUserID(currentUser.id()))) {
            int transactionID = inputChoice(0, transactionRepository.count());
            refundRequestView.createRefundRequest(transactionID);
        }
    }

    private void searchForServices() {
        SearchServicesController searchServicesController = new SearchServicesController(this.getAllServices());
        SearchServicesView searchServicesView = new SearchServicesView(searchServicesController);
        List<ServiceProvider> results = searchServicesView.show();

        if (results.size() > 0) {
            System.out.println("\n" + results.size() + " results found: \n");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i).getServiceName());
            }
            System.out.println(0 + ". Search for something else");

            int choice = inputChoice(0, results.size());

            if (choice == 0) {
                searchForServices();
            } else if (choice <= results.size() && choice > 0) {
                payForService(results.get(choice - 1));
            }
        } else {
            System.out.println("No results found");
        }
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
        PaymentController paymentController = new PaymentController(serviceProvider, transactionRepository, currentUser.id());
        PaymentView paymentView = new PaymentView(paymentController);

        System.out.println("Choose a payment method:\n");
        System.out.println("1. Credit card");
        System.out.println("2. Wallet");
        boolean allowsCashOnDelivery = serviceProvider.allowsCashOnDelivery();
        if (allowsCashOnDelivery)
            System.out.println("3. Cash on delivery");

        int choice = inputChoice(1, 2 + (allowsCashOnDelivery ? 1 : 0));

        PaymentMethod paymentMethod;
        switch (choice) {
            case 2 -> paymentMethod = new WalletPayment(userRepository, currentUser.id());
            case 3 -> paymentMethod = new CashOnDeliveryPayment();
            default -> paymentMethod = new CreditCardPayment();
        }
        paymentView.selectPaymentMethod(paymentMethod);

        Map<String, String> form = new HashMap<>();

        for (PaymentFormField field : serviceProvider.getPaymentFormFields()) {
            System.out.print(field.label() + ": ");
            form.put(field.label(), this.scanner.nextLine());
        }

        paymentController.submitPaymentForm(form);
    }

    private int inputChoice(int start, int end) {
        int choice;
        do {
            System.out.print("\n> ");
            try {
                choice = Integer.parseInt(this.scanner.nextLine());
            } catch (Exception e) {
                choice = -1;
            }
        } while (end < choice || choice < start);
        return choice;
    }

    private void logout() {
        this.currentUser = null;
    }
}
