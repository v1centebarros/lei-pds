package lab07.ex03;

public class Bebida extends Produto{
    public Bebida(String nome, double weight) {
        super(nome, weight);
    }

    @Override
    public String toString() {
        return "Bebida ' "+ getNome()+
                "' - weight: " + getWeight();
    }

    @Override
    public void draw() {
        System.out.println(indent.toString() + toString());
    }
}
