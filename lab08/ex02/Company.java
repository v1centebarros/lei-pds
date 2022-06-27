package lab08.ex02;

import java.util.*;

class Company {
    public static User user;
    private List<Employee> emps = new ArrayList<>();

    private Parking companyParking = new Parking();
    private Insurance companyInsurance = new Insurance("OK!TeleSeguros");
    public void admitEmployee(Person person, double salary) {
        Employee employee = new Employee(person.getName(), salary);
        emps.add(employee);
        // Facade
        if(SocialSecurity.regist(employee)){
            System.out.println("Employee registered to the Social Security");
        }else{
            System.out.println("Error! Employee already registered to the Social Security");
        }
        companyInsurance.register(employee);
        employee.addEmpCard(new Cartao(employee));

        if(this.companyParking.allow(employee)){
            System.out.println("Employee allowed to park\n");
        }else{
            System.out.println("Error! Employee already has parking permissions\n");
        }
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

    public double salaryMedio(){
        double salarySum = 0;
        for (Employee employee : emps){
            salarySum += employee.getSalary();
        }
        return (double) salarySum/emps.size();
    }

}
