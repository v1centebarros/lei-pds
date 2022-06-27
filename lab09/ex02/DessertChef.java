package lab09.ex02;

public class DessertChef extends Chef {
    @Override
    public void parse(String request) {
        if (canHandleRequest(request,"dessert")) {
            System.out.println("DessertChef: Starting to cook " + request + ". Out in " + this.randomTime() + " minutes!");
        }
        else {
            System.out.println("DessertChef: I can't cook that.");
            super.parse(request);
        }

    }
}