package lab11.ex03;

public class Emprestado implements State {

    @Override
    public void regista(Book book) {
        throw new IllegalStateException("Operação não disponível");
    }

    @Override
    public void requisita(Book book) {
        throw new IllegalStateException("Operação não disponível");
    }

    @Override
    public void reserva(Book book) {
        throw new IllegalStateException("Operação não disponível");

    }

    @Override
    public void devolve(Book book) {
        book.setState(new Disponivel());
    }

    @Override
    public void cancelaReserva(Book book) {
        throw new IllegalStateException("Operação não disponível");
    }

    @Override
    public String toString() {
        return "[ Emprestado ]";
    }
}
