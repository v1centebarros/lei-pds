package lab07.ex01;

import java.util.Date;

public abstract  class DecatorEmployee implements InterfaceEmployee{
    protected InterfaceEmployee employee;

    public DecatorEmployee(InterfaceEmployee employee) {
        this.employee = employee;
    }

    @Override
    public String getNome() {
        return employee.getNome();
    }

    @Override
    public void start(Date data) {
        employee.start(data);
    }

    @Override
    public boolean terminate(Date data) {
        return employee.terminate(data);
    }

    @Override
    public boolean work(int num) {
        return employee.work(num);
    }
}
