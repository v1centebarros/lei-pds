package lab10.ex03;

public class Main {
    public static void main(String[] args) {
        Mediator mb = new Mediator();
        new Central(mb).start();
        new Central(mb).start();
        new Taxi(mb).start();
        new Taxi(mb).start();
        new Taxi(mb).start();
        new Taxi(mb).start();

    }

}
