import auth.*;
import discount.DiscountRepository;
import discount.InMemoryDiscountRepository;
import payment.*;
import refund.*;
import search.SearchServicesController;
import search.SearchServicesView;
import service.*;
import transactions.InMemoryTransactionRepository;
import transactions.Transaction;
import transactions.TransactionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

//        SearchServicesController searchServicesController = new SearchServicesController(List.of(
//                new VodafoneRecharge()
//        ));
//        SearchServicesView searchServicesView = new SearchServicesView(searchServicesController);
//
//        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
//        DiscountRepository discountRepository = new InMemoryDiscountRepository();
//
//        PaymentController paymentController = new PaymentController(new VodafoneRecharge(), transactionRepository, discountRepository, currentUser.id());
//

    }

    private void adminMenu() {
        while (this.currentUser != null) {
            System.out.println("1. Manage discounts\n" +
                    "2. Manage refund requests\n" +
                    "3. Logout\n");
            int choice = inputChoice(1, 3);
            switch (choice) {
                case 1 -> {
                    this.manageDiscounts();
                }
                case 2 -> {
                    this.manageRefundRequests();
                }
                case 3 -> {
                    this.logout();
                }
                default -> {
                    System.out.println("Invalid option");
                }
            }
        }
    }

    private void manageRefundRequests() {
        RefundResponseController refundResponseController = new RefundResponseController(refundRepository);
        RefundResponseView refundResponseView = new RefundResponseView(refundResponseController);
        refundResponseView.show();

        System.out.println("Select a refund request by its ID");
        int requestID = inputChoice(0, refundRepository.count());

        System.out.println("Request state: " +
                "1. Accept" +
                "2. Reject" +
                "3. Keep pending"
        );
        RefundStatus refundStatus;
        switch (inputChoice(0, refundRepository.count())) {
            case 1 -> {
                refundStatus = RefundStatus.Accepted;
            }
            case 2 -> {
                refundStatus = RefundStatus.Rejected;
            }
            default -> {
                refundStatus = RefundStatus.Pending;
            }
        }

        refundResponseView.setRefundStatus(requestID, refundStatus);
    }

    private void manageDiscounts() {
        System.out.println("\nAdd a discount on:\n");
        for (int i = 0; i < getAllServices().size(); i++) {
            System.out.println((i + 1) + ". " + getAllServices().get(i).getServiceName());
        }
        System.out.println("0. All services");
        int choice = inputChoice(0, getAllServices().size() - 1);

//        TODO: Check if user chose 0, then add new overall discount rather than specific discount
        discountRepository.addNewSpecificDiscount(inputChoice(1, 99), getAllServices().get(choice).getServiceName());
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
            System.out.println("1. Search for services\n" +
                    "2. Make a refund request\n" +
                    "3. See my refund requests\n" +
                    "4. Add funds to wallet\n" +
                    "5. Logout\n");
            int choice = inputChoice(1, 4);
            switch (choice) {
                case 1 -> {
                    this.searchForServices();
                }
                case 2 -> {
                    this.requestRefund();
                }
                case 3 -> {
                    this.myRequestRequests();
                }
                case 4 -> {
                    this.addFundsToWallet();
                }
                case 5 -> {
                    this.logout();
                }
                default -> {
                    System.out.println("Invalid option");
                }
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
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i).getServiceName());
            }
            System.out.println(0 + ". Search for something else");

            int choice = inputChoice(0, results.size());

            if (choice == 0) {
                searchForServices();
            }
            if (choice <= results.size() && choice > 0) {
                payForService(results.get(choice - 1));
            }
        } else {
            System.out.println("No results found");
            searchForServices();
        }
    }

    private List<ServiceProvider> getAllServices() {
        return List.of(
                new VodafoneRecharge(),
                new We()
        );
    }

    private void payForService(ServiceProvider serviceProvider) {
        PaymentController paymentController = new PaymentController(serviceProvider, transactionRepository, discountRepository, currentUser.id());
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
            case 2 -> {
                paymentMethod = new WalletPayment();
            }
            case 3 -> {
                paymentMethod = new CashOnDeliveryPayment();
            }
            default -> {
                paymentMethod = new CreditCardPayment();
            }
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
            choice = Integer.parseInt(this.scanner.nextLine());
        } while (end < choice && choice < start);
        return choice;
    }

    private void logout() {
        this.currentUser = null;
    }
}
