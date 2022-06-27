package lab05.ex2;

public class YogurtCakeBuilder implements CakeBuilder {

    protected Cake yogurtCake;

    public void setCakeShape(Shape shape) {
        yogurtCake.setShape(shape);
    }
    public void addCakeLayer() {
        yogurtCake.addCakeLayer();
    }
    public void addCreamLayer() {
        Cream cream = new Cream("Vanilla");
        yogurtCake.setMidLayerCream(cream);
    }
    public void addTopLayer() {
        Cream cream = new Cream("Red_Berries");
        yogurtCake.setTopLayerCream(cream);
    }
    public void addTopping() {
        Topping topping = new Topping("Chocolate");
        yogurtCake.setTopping(topping);
    }
    public void addMessage(String m){
        yogurtCake.setMessage(m);
    }
    public void createCake(){
        yogurtCake = new Cake("Yogurt");
    }
    public Cake getCake(){
        return yogurtCake;
    }
    
}
