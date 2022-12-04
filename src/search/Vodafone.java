package search;

public class Vodafone implements ServiceProvider{
    @Override
    public void handle(String service) {
        System.out.println("vodafone handled");
    }
}
