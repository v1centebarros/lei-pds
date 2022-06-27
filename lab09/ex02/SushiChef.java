package lab09.ex02;

public class SushiChef extends Chef {
    @Override
    public void parse(String request) {
        if (canHandleRequest(request,"sushi")) {
            System.out.println("SushiChef: Starting to cook " + request + ". Out in " + this.randomTime() + " minutes!");
        }
        else {
            System.out.println("SushiChef: I can't cook that.");
            super.parse(request);
        }

    }
}