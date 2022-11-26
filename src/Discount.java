public class Discount implements Service{

    Service service;

    public Discount(Service service)
    {
        this.service = service;
    }


    @Override
    public void apply() {

    }
}
