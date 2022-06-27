package lab08.ex02;

public class Cartao {
    private int numero;
    private Person holder;
    private static int count = 0;

    public Cartao(Person person) {
        this.holder = person;
        this.numero = ++Cartao.count;
    }

    public int getNumero() {
        return numero;
    }

    public Person getHolder() {
        return holder;
    }

    public String toString(){
        return String.format("Company Card of %s, with id %d", holder.getName(), numero);
    }
}
