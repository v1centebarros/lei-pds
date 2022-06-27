package lab05.ex2;

public class ChocolateCakeBuilder implements CakeBuilder {

    protected Cake chocolateCake;

    public void setCakeShape(Shape shape) {
        chocolateCake.setShape(shape);
    }
    public void addCakeLayer() {
        chocolateCake.addCakeLayer();
    }
    public void addCreamLayer() {
        Cream cream = new Cream("null");
        chocolateCake.setMidLayerCream(cream);
    }
    public void addTopLayer() {
        Cream cream = new Cream("Whipped_cream");
        chocolateCake.setTopLayerCream(cream);
    }
    public void addTopping() {
        Topping topping = new Topping("Fruit");
        chocolateCake.setTopping(topping);
    }
    public void addMessage(String m){
        chocolateCake.setMessage(m);
    }
    public void createCake(){
        chocolateCake = new Cake("Soft chocolate");
    }
    public Cake getCake(){
        return chocolateCake;
    }
}
