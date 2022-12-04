import auth.*;
import discount.DiscountRepository;
import discount.InMemoryDiscountRepository;
import payment.*;
import search.SearchServicesController;
import search.SearchServicesView;
import service.ServiceProvider;
import service.VodafoneRecharge;
import transactions.InMemoryTransactionRepository;
import transactions.TransactionRepository;

import java.util.List;
import java.util.Scanner;

public class Fawry {
    private User currentUser = null;
    private final Scanner scanner = new Scanner(System.in);

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
//        RefundRepository refundRepository = new InMemoryRefundRepository();

    }

    private void adminMenu() {
        while (this.currentUser != null) {
            System.out.println("1. Add discounts\n" +
                    "2. Manage refund requests\n" +
                    "3. Logout\n");
            int choice = inputChoice(1, 3);
            switch (choice) {
                case 1 -> {
                    System.out.println("Add discounts".toUpperCase());
                }
                case 2 -> {
                    System.out.println("Manage refund requests".toUpperCase());
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

    public void auth() {
        UserRepository userRepository = new InMemoryUserRepository();
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
                    "3. Add funds to wallet\n" +
                    "4. Logout\n");
            int choice = inputChoice(1, 4);
            switch (choice) {
                case 1 -> {
//                    System.out.println("Search for services".toUpperCase());
                    this.searchForServices();
                }
                case 2 -> {
                    System.out.println("Make a refund request".toUpperCase());
                }
                case 3 -> {
                    System.out.println("Add funds to wallet".toUpperCase());
                }
                case 4 -> {
                    this.logout();
                }
                default -> {
                    System.out.println("Invalid option");
                }
            }
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
                new VodafoneRecharge()
        );
    }

    private void payForService(ServiceProvider serviceProvider) {
        TransactionRepository transactionRepository = new InMemoryTransactionRepository();
        DiscountRepository discountRepository = new InMemoryDiscountRepository();

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
        paymentView.fillPaymentForm();
    }

    private int inputChoice(int start, int end) {
        int choice;
        do {
            System.out.print("\n> ");
            choice = this.scanner.nextInt();
        } while (end <= choice && choice >= start);
        return choice;
    }

    private void logout() {
        this.currentUser = null;
    }
}
