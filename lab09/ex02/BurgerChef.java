package lab09.ex02;

public class BurgerChef extends Chef {
    @Override
    public void parse(String request) {
        if (canHandleRequest(request,"burger")) {
            System.out.println("BurgerChef: Starting to cook " + request + ". Out in " + this.randomTime() + " minutes!");
        }
        else {
            System.out.println("BurgerChef: I can't cook that.");
            super.parse(request);
        }

    }
}