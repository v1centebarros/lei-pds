package lab06.ex01;
import static java.lang.System.*;
public class Main {

    public static void main(String[] args) {

        // ------------ SEM DO ADAPTER ------------
        out.println("------------ EMPLOYEE/DATABASE SYSTEM ------------");
        Employee empl1 = new Employee("Tommy Shelby", 1, 1500);
        Employee empl2 = new Employee("Polly Gray", 2, 3100);
        Employee empl3 = new Employee("Arthur Shelby", 3, 2000);
        Database db = new Database();

        out.println("Before we add the employees...");
        out.println(db);
        out.println("Let's add all of them.");
        db.addEmployee(empl1);
        db.addEmployee(empl2);
        db.addEmployee(empl3);
        out.println(db);
        out.println("Now let's remove Tommy...");
        db.deleteEmployee(1);
        out.println(db);

        out.println("");

        out.println("------------ EMPREGADO/REGISTOS SYSTEM ------------");
        Empregado empr1 = new Empregado("James", "Bond", 100, 1400);
        Empregado empr2 = new Empregado("Auric", "Goldfinger", 530, 1100);
        Empregado empr3 = new Empregado("Vesper", "Lynd", 76, 3000);
        Registos reg = new Registos();
        out.println("Before we add the empregados...");
        out.println(reg);
        out.println("Let's add all of them.");
        reg.insere(empr1);
        reg.insere(empr2);
        reg.insere(empr3);
        out.println(reg);
        out.println("Now let's remove Goldfinger...");
        reg.remove(530);
        out.println(reg);

        out.println("");

        out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

        // ------------ COM DO ADAPTER ------------
        Worker e4 = new Worker("Bob", "Boss",11,1234);
        Worker e5 = new Worker("Jeff", "Feff",13,1024);
        Worker e6 = new Worker("Rose", "Rosa",23,999);

        AdaptorSweets adapSw = new AdaptorSweets(db);
        adapSw.addWorker(e4);
        adapSw.addWorker(e5);
        adapSw.addWorker(e6);
        System.out.println("Registos:\n" + adapSw);


        AdaptorPetiscos adaptPt = new AdaptorPetiscos(reg);
        adaptPt.addWorker(e4);
        adaptPt.addWorker(e5);
        adaptPt.addWorker(e6);
        System.out.println("Database:\n" + adaptPt);

        System.out.println("O Jeff Feff está nos registos? " + adapSw.isWorker(13));
        System.out.println("O Jeff Feff está na database? " + adaptPt.isWorker(13));

        System.out.println("");

        System.out.println("Vamos remover o Gonçalo Dias...");
        adaptPt.removeWorker(238);
        System.out.println("Registos:\n" + adapSw);
        adaptPt.removeWorker(238);
        System.out.println("Database:\n" + adaptPt);
    }
}