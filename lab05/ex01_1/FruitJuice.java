package lab05.ex01_1;

public class FruitJuice extends PortionFactory{
    private String fruitName;

    FruitJuice(String fruitName){
        super();
        setTemperature(Temperature.COLD);
        setState(State.Liquid);
        this.fruitName = fruitName;
    }

    @Override
    public String toString(){
        return "FruitJuice: " + this.fruitName + ", Temperatura " + this.getTemperature() + ", State " + this.getState();
    }
}
