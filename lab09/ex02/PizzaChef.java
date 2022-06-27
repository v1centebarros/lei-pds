package lab09.ex02;

public class PizzaChef extends Chef {
    @Override
    public void parse(String request) {
        if (canHandleRequest(request,"pizza")) {
            System.out.println("PizzaChef: Starting to cook " + request + ". Out in " + this.randomTime() + " minutes!");
        }
        else {
            System.out.println("PizzaChef: I can't cook that.");
            super.parse(request);
        }

    }
}