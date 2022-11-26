package payment;

public class Context {
    PaymentMethod strategy;

    public void setStrategy(PaymentMethod strategy)
    {
        this.strategy = strategy;
    }

    public void executeStrategy(int amount){

        strategy.pay(amount);
    }
}
