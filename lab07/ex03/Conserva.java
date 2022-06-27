package lab07.ex03;

public class Conserva extends Produto {
    public Conserva(String nome, double weight) {
        super(nome, weight);
    }

    @Override
    public String toString() {
        return "Conserva ' "+ getNome()+
                "' - weight: " + getWeight();
    }

    @Override
    public void draw() {
        System.out.println(indent.toString() + toString());
    }
}
