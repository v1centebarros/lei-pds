package lab11.ex03;


public class Disponivel implements State {

    @Override
    public void regista(Book book) {
        throw new IllegalStateException("Operação não disponível");
    }

    @Override
    public void requisita(Book book) {
        book.setState(new Emprestado());
    }

    @Override
    public void reserva(Book book) {
        book.setState(new Reservado());
    }

    @Override
    public void devolve(Book book) {
        throw new IllegalStateException("Operação não disponível");
    }

    @Override
    public void cancelaReserva(Book book) {
        throw new IllegalStateException("Operação não disponível");
    }


    @Override
    public String toString() {
        return "[ Disponível ]";
    }
}