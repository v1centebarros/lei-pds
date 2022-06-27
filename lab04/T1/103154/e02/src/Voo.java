import java.util.*;

public class Voo implements VooInterface {

	private int countReservaId;
	private String codigo;
	private Aviao aviao;
	private HashMap<Integer, Reserva> reservas;

	public Voo(String codigo, int nFilasE, int nLugaresFilaE, int nFilasT, int nLugaresFilaT) {

		this.countReservaId = 1;
		this.codigo = codigo;
		this.aviao = new Aviao(nFilasE, nLugaresFilaE, nFilasT, nLugaresFilaT);
		this.reservas = new HashMap<>();
	}

	public String getCodigo() {
		return codigo;
	}

	public Aviao getAviao() {
		return aviao;
	}

	public Reserva getReserva(int reservaId) {
		return reservas.get(reservaId);
	}

	@Override
	public boolean createReserva(ClasseType classe, int nPassageiros) {

		ArrayList<Integer> emptyFilaIds = aviao.getEmptyFilaIdsBy(classe); // Ids das filas livres
		ArrayList<String> emptyFilaLugarIds = aviao.getEmptyFilaLugarIdsBy(classe); // Ids filaLugar dos lugares livres

		// Criar reserva:
		Reserva reserva = new Reserva(countReservaId, classe, nPassageiros);
		int reservaId = reserva.getId();

		// Não há lugares suficientes:
		if (emptyFilaLugarIds.size() < nPassageiros) {
			System.err.println("Não foi possível obter lugares para a reserva: " + reserva);
			return false;
		}

		// Adicionar reserva:
		reservas.put(reservaId, reserva);
		countReservaId++;

		// Índice em emptyLugarFilaIds:
		int index = (emptyFilaIds.size() > 0) // Há pelo menos uma fila vazia
				? emptyFilaLugarIds.indexOf(emptyFilaIds.get(0) + "A") // Índice do primeiro lugar vazio da primeira fila vazia
				: 0; // Índice do primeiro lugar vazio

		// Enquanto houver passageiros sem lugar:
		do {
			String filaLugarId = emptyFilaLugarIds.get(index);
			aviao.setReservaIdOf(filaLugarId, reservaId);
			index = (index + 1) % emptyFilaLugarIds.size(); // Lista circular
		} while (--nPassageiros > 0);

		return true;
	}

	@Override
	public boolean cancelReserva(int reservaId) {

		if (this.reservas.remove(reservaId) == null)
			return false;

		// Colocar reservaId == 0 nos lugares
		for (String filaLugarId : aviao.getFilaLugarIdsBy(reservaId)) {
			aviao.setReservaIdOf(filaLugarId, 0);
		}
		return true;

	}

	@Override
	public void printMapOfReservas() {

		int nFilasE = aviao.getnFilasBy(ClasseType.E);
		int nFilasT = aviao.getnFilasBy(ClasseType.T);
		int nLugaresFilaE = aviao.getnLugaresFilaBy(ClasseType.E);
		int nLugaresFilaT = aviao.getnLugaresFilaBy(ClasseType.T);

		// Imprime cabeçalho:
		System.out.print("   ");
		for (int i = 0; i < nFilasE + nFilasT; i++)
			System.out.printf("%3d", i + 1);
		System.out.println();

		// Imprime linhas:
		int i = 0;
		while (i < nLugaresFilaE && i < nLugaresFilaT) {
			char lugarId = (char) ('A' + i);
			int filaId = 1;
			// Classe executiva:
			while (filaId++ <= nFilasE) {
				if (i < nLugaresFilaE) {
					int reservaId = aviao.getReservaIdOf(filaId + "" + lugarId);
					System.out.printf("%3d", reservaId);
				} else {
					System.out.print("   ");
				}
			}
			// Classe turística:
			while (filaId++ <= (nFilasE + nFilasT)) {
				if (i < nLugaresFilaT) {
					int reservaId = aviao.getReservaIdOf(filaId + "" + lugarId);
					System.out.printf("%3d", reservaId);
				} else {
					System.out.print("   ");
				}
			}
			System.out.println();
			i++;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Código de voo " + codigo + ". ");
		sb.append("Lugares disponíveis: ");
		if (aviao.hasExecutiva())
			sb.append(aviao.getnLugaresBy(ClasseType.E) + " lugares em classe " + ClasseType.E.getLabel() + "; ");
		sb.append(aviao.getnLugaresBy(ClasseType.T) + " lugares em classe " + ClasseType.T.getLabel() + ".");
		sb.append("\n");
		return sb.toString();
	}

}
