package lab05.ex01_1;

public class Milk extends PortionFactory{
    Milk(){
        super();
        this.setTemperature(Temperature.WARM);
        setState(State.Liquid);
    }

    @Override
    public String toString(){
        return "Milk: " + "Temperatura " + this.getTemperature() + ", State " + this.getState() ;
    }

}
