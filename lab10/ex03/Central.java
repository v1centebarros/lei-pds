package lab10.ex03;

import java.util.concurrent.TimeUnit;

public class Central  extends Thread{

    private Mediator med;
    private int id;
    private static int num = 1;

    public Central(Mediator m) {
        med = m;
        id = num++;

    }

    public void run() {
        int num;
        while (true) {
            try {
                med.storeMessage(num = (int) (Math.random() * 100));
                System.out.println("Central" + id + "-" + num + " ");
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
