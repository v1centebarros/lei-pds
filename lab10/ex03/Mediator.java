package lab10.ex03;
import java.util.LinkedList;
import java.util.Queue;

public class Mediator {
    private Queue<Integer> msgns = new LinkedList<>();

    public synchronized void storeMessage(int msg) {
        msgns.add(msg);
    }

    public synchronized int receiveMessage() {
        if (!msgns.isEmpty())
            return msgns.poll();
        return -1;
    }

    public boolean isEmpty() {
        return msgns.isEmpty();
    }

}
