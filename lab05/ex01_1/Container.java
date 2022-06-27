package lab05.ex01_1;

public class Container {
    protected Portion portion;
    public Container(Portion portion){
        this.portion = portion;
    }

    public Portion getPortion() {
        return portion;
    }

    public static Container create(Portion menu) {

        switch (menu.getState()) {
            case Liquid:
                if (menu.getTemperature()== Temperature.COLD) {
                        return new PlasticBottle(menu);
                }
                return new TermicBottle(menu);
            case Solid:
                if (menu.getTemperature() == Temperature.COLD) {
                        return new PlasticBag(menu);
                }
                return new Tupperware(menu);

            default:
                System.out.println("Error! Unknown State enum declared!");
                System.exit(1);
        }
        return null;
    }
}
class PlasticBottle extends Container{

    PlasticBottle(Portion portion){
        super(portion);
    }

    @Override
    public String toString(){
        return "PlasticBottle with portion = " + getPortion().toString();
    }
}

class TermicBottle extends Container{

    TermicBottle(Portion portion){
        super(portion);
    }

    @Override
    public String toString(){
        return "TermicBottle with portion = " + getPortion().toString();
    }
}

class Tupperware extends Container{

    Tupperware(Portion portion){
        super(portion);
    }

    @Override
    public String toString(){
        return "Tupperware with portion = " + getPortion().toString();
    }
}


class PlasticBag extends Container {

    PlasticBag(Portion portion) {
        super(portion);
    }

    @Override
    public String toString() {
        return "PlasticBag with portion = " + getPortion().toString();
    }

}

