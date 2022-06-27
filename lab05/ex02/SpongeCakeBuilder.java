package lab05.ex2;

public class SpongeCakeBuilder implements CakeBuilder {

    protected Cake spongeCake;

    public void setCakeShape(Shape shape) {
        spongeCake.setShape(shape);
    }
    public void addCakeLayer() {
        spongeCake.addCakeLayer();
    }
    public void addCreamLayer() {
        Cream cream = new Cream("Red_Berries");
        spongeCake.setMidLayerCream(cream);
    }
    public void addTopLayer() {
        Cream cream = new Cream("Whipped_Cream");
        spongeCake.setTopLayerCream(cream);
    }
    public void addTopping() {
        Topping topping = new Topping("Fruit");
        spongeCake.setTopping(topping);
    }
    public void addMessage(String m){spongeCake.setMessage(m);}
    public void createCake(){
        spongeCake = new Cake("Sponge");
    }
    public Cake getCake(){
        return spongeCake;
    }
    
}
