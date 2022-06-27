package lab08.ex01.alineab;

class Employee {

    private double salary;
    private Person person;
    private BankAccount bankAccount;

    public Employee(Person person, double s) {
        this.person = person;
        salary = s;
        bankAccount = new BankAccountProxy(new BankAccountImpl("PeDeMeia", 0));
    }

    public double getSalary() {
        return salary;
    }

    public Person getPerson() {
        return person;
    }

    public BankAccount getBankAccount(){
        return bankAccount;
    }
}
