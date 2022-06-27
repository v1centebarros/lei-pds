package lab05.ex2;

public class Cake {
    private Shape shape = Shape.Circle;
    private final String cakeLayer;
    private int numCakeLayers = 1;
    private Cream midLayerCream = new Cream("null");
    private Cream topLayerCream;
    private Topping topping;
    private String message = "";
    //.. restantes métodos
    public Cake(String cakeLayer) {
        this.cakeLayer = cakeLayer;
    }
    public void setShape(Shape shape) {
        this.shape = shape;
    }
    public void addCakeLayer() {
        this.numCakeLayers++;
    }
    public void setMidLayerCream(Cream midLayerCream) {
        this.midLayerCream = midLayerCream;
    }
    public void setTopLayerCream(Cream topLayerCream) {
        this.topLayerCream = topLayerCream;
    }
    public void setTopping(Topping topping) {
        this.topping = topping;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(cakeLayer).append(" cake with ").append(numCakeLayers).append(" layers");
        if (!midLayerCream.getName().equals("null")) {
            sb.append(" and ").append(midLayerCream).append(" cream");
        }
        sb.append(", topped with ").append(topLayerCream).append(" cream and ").append(topping).append(".");
        if (message.length() != 0) {
            sb.append(" Message says: \"").append(message).append("\".");
        }
        sb.append("\n");
        return sb.toString();
    }
}