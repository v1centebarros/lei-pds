
public class Reserva {

	private ClasseType classe;
	private int nPassageiros;
	private int id;

	public Reserva(int id, ClasseType classe, int nPassageiros) {

		this.id = id;
		this.classe = classe;
		this.nPassageiros = nPassageiros;
	}

	public int getId() {
		return id;
	}

	public ClasseType getClasse() {
		return classe;
	}

	public int getnPassageiros() {
		return nPassageiros;
	}

	@Override
	public String toString() {
		return classe.toString() + " " + nPassageiros;
	}
}
