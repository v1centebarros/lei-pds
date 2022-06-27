package lab07.ex01;

import java.util.Date;

public class TeamLeader extends DecatorEmployee{
    public TeamLeader(InterfaceEmployee employee){
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
            System.out.print("TeamLeader...");
            return true;
        }
        return false;
    }

    public void plan(){
        System.out.println(super.getNome()+" works as planner!");
    }
}
