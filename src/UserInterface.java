import service.Service;

import java.util.List;

public interface UserInterface {
     void requestFund(Service service, Transaction transaction );
     void checkoutDiscount(Service service);
     void signUp(String userName, String email, String password);
     void signIn(String userName, String password);
     List<Service> searchForService(String query);
     void payForService(Service service);
}
