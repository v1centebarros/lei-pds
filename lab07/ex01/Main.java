package lab07.ex01;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        InterfaceEmployee employee1 = new Employee("MARIA");
        TeamMember employee2 = new TeamMember(new Employee("RUI"));
        TeamMember employee3 = new TeamMember(new Employee("JOANA"));
        Manager employee4 = new Manager(new Employee("MATILDE"));
        Manager employee5 = new Manager(new Employee("FILIPE"));
        TeamLeader employee6 = new TeamLeader(new Employee("MATEUS"));

        System.out.println("-----------------------------teste1-------------------------\n");
        employee1.work(1); //nao tem trabalho atribuido
        employee1.start(new Date()); //começou um trabalho
        employee1.start(new Date()); //tem outro trabalho

        employee3.start(new Date()); //começou um trabalho
        employee3.terminate(new Date()); //terminou trabalho

        employee1.work(1);//esta a trabalhar
        employee1.terminate(new Date()); //terminou trabalho

        employee2.colaborate();
        employee6.plan();

        System.out.println("\n-----------------------------terminou1-------------------------");
        System.out.println("-----------------------------teste2-------------------------");
        InterfaceEmployee employees[] = {employee2, employee3, employee4, employee5,employee6};
        for (InterfaceEmployee e: employees) {
            System.out.println("-----------------outro---------------------");
            e.start(new Date());
            e.work(1);

            // For last role added, do additional operation
            if (e instanceof TeamMember) {
                TeamMember ee = (TeamMember) e;
                ee.colaborate();
            }
            if (e instanceof TeamLeader) {
                TeamLeader ee = (TeamLeader) e;
                ee.plan();
            }
            if (e instanceof Manager) {
                Manager ee = (Manager) e;
                ee.manage();
            }
            e.terminate(new Date());
            System.out.println("--------------------------------------\n");
        }
        System.out.println("\n-----------------------------terminou2------------------------");

    }
}
