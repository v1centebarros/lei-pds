package lab10.EX02;

public class NullProgrammer extends Employee{
    public NullProgrammer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return "There aren't any " + this.name + " programmers!";
    }
}
