package lab07.ex03;

public abstract class Produto implements InterfaceProdutos{
    static StringBuffer indent = new StringBuffer();
    private String nome;
    private double weight;

    public Produto(String nome, double weight) {
        this.nome = nome;
        this.weight = weight;
    }

    public String getNome() {
        return nome;
    }

    public double getWeight() {
        return weight;
    }

    public abstract void draw();
}
