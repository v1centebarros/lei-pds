package lab08.ex01.alineab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Company {
    public static User user;
    private List<Employee> emps = new ArrayList<>();

    public void admitEmployee(Person person, double salary) {
        emps.add(new Employee(person, salary));
    }

    public void paySalaries(int month) {
        for (Employee e : emps) {
            BankAccount ba = e.getBankAccount();
            ba.deposit(e.getSalary());
        }
    }

    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }
}
