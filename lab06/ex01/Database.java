package lab06.ex01;

import java.util.Vector;

public class Database {
    private Vector<Employee> employees;

    public Database () {
        employees = new Vector<>();
    }

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public void deleteEmployee(long emp_num) {
        employees.removeIf(e -> emp_num == e.getEmpNum());
    }
    public Employee[] getAllEmployees() {
        Employee[] eArray = new Employee[employees.size()];
        for (int i = 0; i < eArray.length; i++) {
            eArray[i] = employees.get(i);
        }
        return eArray;
    }

    public boolean isAlreadyEmployee (long code) {
        for (Employee e: employees) {
            if (e.getEmpNum() == code){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Database: " + employees;
    }
}