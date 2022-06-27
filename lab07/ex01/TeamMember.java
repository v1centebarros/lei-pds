package lab07.ex01;

import java.util.Date;

public class TeamMember extends DecatorEmployee{
    public TeamMember(InterfaceEmployee employee){
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
            System.out.print("TeamMember ...");
            return true;
        }
        return false;
    }

    //Additional operation
    public void colaborate() {
        System.out.print(super.getNome()+ " works as colaborator!\n");
    }
}
