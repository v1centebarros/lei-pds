package lab05.ex01_1;

public abstract class PortionFactory implements Portion {

    private State state;
    private Temperature temp;

    public State getState(){
        return this.state;
    }
    public Temperature getTemperature(){
        return this.temp;
    }
    public void setTemperature(Temperature t){
        this.temp = t;
    }
    public void setState(State state){
        this.state = state;
    }

    public String toString(){
        return "";
    }

    public static Portion create(String nome, Temperature t) {
        switch (nome){
            case "Beverage":
                switch (t){
                    case COLD:
                        return new FruitJuice("Orange");
                    case WARM:
                        return new Milk();
                    default:
                        System.out.println("Error! Unknown Temperature enum declared!");
                        System.exit(1);
                }
            case "Meat":
                switch (t){
                    case COLD:
                        return new Tuna();

                    case WARM:
                        return new Pork();

                    default:
                        System.out.println("Error! Unknown Temperature enum declared!");
                        System.exit(1);
                }
            default:
                System.out.println("Error!");
                System.exit(1);
        }
        return null;
    }
}
