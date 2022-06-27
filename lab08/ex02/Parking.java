package lab08.ex02;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    private static List<Person> allowedEmployees;

    public Parking(){
        this.allowedEmployees = new ArrayList<>();
    }

    public boolean allow(Person p){
        if(this.allowedEmployees.contains(p)){
            return false;
        }else{
            this.allowedEmployees.add(p);
            return true;
        }
    }

    public static List<Person> getAllowedEmployees() {
        return allowedEmployees;
    }
}