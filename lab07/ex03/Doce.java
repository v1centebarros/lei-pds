package lab07.ex03;

public class Doce extends Produto {
    public Doce(String nome, double weight) {
        super(nome, weight);
    }

    @Override
    public String toString() {
        return "Doce '"+ getNome()+
                "' - weight: " + getWeight();
    }
    @Override
    public void draw() {
        System.out.println(indent.toString() + toString());
    }
}
