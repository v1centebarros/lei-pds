package lab06.ex01;

public class Worker {
    private String name;
    private String surname;
    private long code;
    private double salary;

    public Worker(String name, String surname, int code, double salary) {
        this.name = name;
        this.surname = surname;
        this.code = code;
        this.salary = salary;
    }

    public Worker(String name, long code, double salary) {
        this.name = name;
        this.code = code;
        this.salary = salary;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public long code() {
        return code;
    }

    public double salary() {
        return salary;
    }
}
