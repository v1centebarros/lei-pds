package lab08.ex02;

class Employee extends Person {
    private double salary;
    private Cartao cartaoEmployee;

    public Employee(String n, double s) {
        super(n);
        salary = s;
        this.cartaoEmployee = null;
    }

    public double getSalary() {
        return salary;
    }

    public Cartao getCartaoEmployee() {
        return cartaoEmployee;
    }

    public void addEmpCard(Cartao cartaoEmployee) {
        this.cartaoEmployee = cartaoEmployee;
    }
}
