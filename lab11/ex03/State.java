package lab11.ex03;

public interface State {
    void regista(Book book);
    void requisita(Book book);
    void reserva(Book book);
    void devolve(Book book);
    void cancelaReserva(Book book);

}
