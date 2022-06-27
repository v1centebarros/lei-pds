package lab10.ex03;

import java.util.concurrent.TimeUnit;

public class Taxi extends Thread {

    private Mediator med;
    private int id;
    private static int num = 1;

    public Taxi(Mediator m) {
        med = m;
        id = num++;
    }

    public void run() {
        while (true) {
            while (med.isEmpty()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("\tTaxi" + id + "-" + med.receiveMessage());
        }
    }

}