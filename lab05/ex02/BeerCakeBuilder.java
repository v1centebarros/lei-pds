package lab05.ex2;

public class BeerCakeBuilder implements CakeBuilder{
    protected Cake beerCake;

    public void setCakeShape(Shape shape) {
        beerCake.setShape(shape);
    }
    public void addCakeLayer() {
        beerCake.addCakeLayer();
    }
    public void addCreamLayer() {
        Cream cream = new Cream("Super Bock");
        beerCake.setMidLayerCream(cream);
    }
    public void addTopLayer() {
        Cream cream = new Cream("Pilsner");
        beerCake.setTopLayerCream(cream);
    }
    public void addTopping() {
        Topping topping = new Topping("Bagaço");
        beerCake.setTopping(topping);
    }
    public void addMessage(String m){
        beerCake.setMessage(m);
    }
    public void createCake(){
        beerCake = new Cake("Champomi");
    }
    public Cake getCake(){
        return beerCake;
    }
}
