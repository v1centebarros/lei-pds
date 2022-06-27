
public interface VooInterface {

	public boolean createReserva(ClasseType classe, int nPassageiros);

	public boolean cancelReserva(int reservaId);

	public void printMapOfReservas();
}
