package lab11.ex03;

public class Book {

    private String title;
    private String author;
    private State state;

    public Book(String t, String a) {
        title = t;
        author = a;
        state = new Inventario();
    }

    public void setState(State s) {
        state = s;
    }

    public void regista() {
        state.regista(this);
    }
    public void reserva() {
        state.reserva(this);
    }
    public void requisita() {
        state.requisita(this);
    }
    public void devolve() {
        state.devolve(this);
    }
    public void cancelaReserva() {
        state.cancelaReserva(this);
    }

    public String toString() {
        return String.format("%-30s %-30s %-30s",  title, author,state);
    }

}