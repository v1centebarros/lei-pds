package lab05.ex2;

public class CakeMaster {

    Cake cake;
    CakeBuilder cakeBuilder;

    public void setCakeBuilder(CakeBuilder builder) {
        this.cakeBuilder = builder;
    }
    public void createCake(String message) {
        cakeBuilder.createCake();
        cakeBuilder.addMessage(message);
        updateCake();
        cake = cakeBuilder.getCake();
    }
    public void createCake(int numLayers, String message) {
        cakeBuilder.createCake();
        for (int i=1; i<numLayers; i++) {
            cakeBuilder.addCakeLayer();
        }
        cakeBuilder.addMessage(message);
        if (numLayers > 1) 
            cakeBuilder.addCreamLayer();
        updateCake();
        cake = cakeBuilder.getCake();
    }
    public void createCake(Shape shape, int numLayers, String message) {
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(shape);
        for (int i=1; i<numLayers; i++) {
            cakeBuilder.addCakeLayer();
        }
        cakeBuilder.addMessage(message);
        updateCake();
        if (numLayers > 1) 
            cakeBuilder.addCreamLayer();
        cake = cakeBuilder.getCake();
    }
    public Cake getCake() {
        return cake;
    }

    private void updateCake() {
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
    }
    
}
