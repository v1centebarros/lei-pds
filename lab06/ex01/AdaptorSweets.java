package lab06.ex01;

import java.util.Arrays;

public class AdaptorSweets implements Adaptor{

    private Database db;

    public AdaptorSweets(Database db) {
        this.db = db;
    }

    @Override
    public void addWorker(Worker worker) {
        db.addEmployee(new Employee(worker.name(), worker.code(), worker.salary()));
    }

    @Override
    public void removeWorker(long code) {
        db.deleteEmployee(code);
    }

    @Override
    public boolean isWorker(long code) {
        return db.isAlreadyEmployee(code);
    }

    @Override
    public void listWorkers() {
        for (Employee e : db.getAllEmployees()) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(db.getAllEmployees());
    }
}
