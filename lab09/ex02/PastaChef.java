package lab09.ex02;

public class PastaChef extends Chef {
    @Override
    public void parse(String request) {
        if (canHandleRequest(request,"pasta")) {
            System.out.println("PastaChef: Starting to cook " + request + ". Out in " + this.randomTime() + " minutes!");
        }
        else {
            System.out.println("PastaChef: I can't cook that.");
            super.parse(request);
        }

    }
}