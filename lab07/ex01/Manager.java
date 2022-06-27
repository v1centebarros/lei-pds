package lab07.ex01;

import java.util.Date;

public class Manager extends DecatorEmployee{
    public Manager(InterfaceEmployee employee){
        super(employee);
    }

    public void start(Date data) {
        super.start(data);
    }

    public boolean terminate(Date data) {
        return super.terminate(data);
    }

    public boolean work(int num) {
        if (employee.work(num)) {
            System.out.print("Manager....");
            return true;
        }
        return false;
    }

    public void manage() {
        System.out.print(super.getNome() + " works as Manage!\n");
    }

}
