import java.util.ArrayList;

public class Aviao implements AviaoInterface {

	private ArrayList<Fila> filasE;
	private ArrayList<Fila> filasT;

	public Aviao(int nFilasE, int nLugaresFilaE, int nFilasT, int nLugaresFilaT) {

		int i = 1;
		this.filasE = new ArrayList<>(nFilasE);
		while (i <= nFilasE) {
			this.filasE.add(new Fila(i++, nLugaresFilaE));
		}
		this.filasT = new ArrayList<>(nFilasT);
		while (i <= (nFilasE + nFilasT)) {
			this.filasT.add(new Fila(i++, nLugaresFilaT));
		}
	}

	@Override
	public ArrayList<Integer> getEmptyFilaIdsBy(ClasseType classe) {

		ArrayList<Integer> filaIds = new ArrayList<>();
		for (Fila fila : (classe == ClasseType.E) ? filasE : filasT) {
			if (fila.isEmpty())
				filaIds.add(fila.getId());
		}
		return filaIds;
	}

	@Override
	public ArrayList<String> getEmptyFilaLugarIdsBy(ClasseType classe) {

		ArrayList<String> filaLugarIds = new ArrayList<>();
		for (Fila fila : (classe == ClasseType.E) ? filasE : filasT) {
			ArrayList<Character> emptyLugarIds = fila.getEmptyLugarIds();
			for (char lugarId : emptyLugarIds)
				filaLugarIds.add(fila.getId() + "" + lugarId);
		}
		return filaLugarIds;
	}

	@Override
	public void setReservaIdOf(String lugarFilaId, int reservaId) {

		int filaId = Integer.parseInt(lugarFilaId.substring(0, lugarFilaId.length() - 1));
		char lugarId = lugarFilaId.charAt(lugarFilaId.length() - 1);
		// Lugar está na classe executiva:
		if (filaId <= filasE.size()) {
			filasE.get(filaId - 1).setReservaIdOf(lugarId, reservaId);
		}
		// Lugar está na classe turística:
		else {
			filasT.get(filaId - filasE.size() - 1).setReservaIdOf(lugarId, reservaId);
		}
	}

	@Override
	public ArrayList<String> getFilaLugarIdsBy(int reservaId) {

		ArrayList<String> filaLugarIds = new ArrayList<>();
		// Lugares da classe executiva:
		for (Fila fila : filasE) {
			ArrayList<Character> lugarIds = fila.getLugarIdsBy(reservaId);
			for (char lugarId : lugarIds)
				filaLugarIds.add(fila.getId() + "" + lugarId);
		}
		return filaLugarIds;
	}

	@Override
	public int getnFilasBy(ClasseType classe) {

		if (classe == ClasseType.E)
			return filasE.size();
		else
			return filasT.size();
	}

	@Override
	public int getnLugaresFilaBy(ClasseType classe) {

		if (classe == ClasseType.E)
			return filasE.get(0).getnLugares();
		else
			return filasT.get(0).getnLugares();

	}

	@Override
	public int getReservaIdOf(String lugarFilaId) {

		int filaId = Integer.parseInt(lugarFilaId.substring(0, lugarFilaId.length() - 1));
		char lugarId = lugarFilaId.charAt(lugarFilaId.length() - 1);
		// Lugar está na classe executiva:
		if (filaId <= filasE.size()) {
			return filasE.get(filaId - 1).getReservaIdOf(lugarId);
		}
		// Lugar está na classe turística:
		else {
			return filasT.get(filaId - filasE.size() - 1).getReservaIdOf(lugarId);
		}
	}

	@Override
	public boolean hasExecutiva() {

		return filasE.size() > 0;
	}

	@Override
	public int getnLugaresBy(ClasseType classe) {

		if (classe == ClasseType.E)
			return filasE.size() * filasE.get(0).getnLugares();
		else
			return filasT.size() * filasT.get(0).getnLugares();
	}

}
