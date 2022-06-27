package lab06.ex01;

public interface Adaptor {
    void addWorker (Worker worker);
    void removeWorker(long code);
    boolean isWorker (long code);
    void listWorkers();
}
