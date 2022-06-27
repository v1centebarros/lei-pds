
public class Lugar {

	private char id;
	private int reservaId;

	public Lugar(char id) {
		this.id = id;
		this.reservaId = 0;
	}

	public char getId() {
		return id;
	}

	public int getReservaId() {
		return reservaId;
	}

	public void setReservaId(int reservaId) {
		this.reservaId = reservaId;
	}
}
