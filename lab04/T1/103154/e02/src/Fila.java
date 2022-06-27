import java.util.ArrayList;

public class Fila implements FilaInterface {

	private int id;
	private ArrayList<Lugar> lugares;

	public Fila(int id, int nLugares) {

		this.id = id;
		this.lugares = new ArrayList<>(nLugares);
		for (int i = 0; i < nLugares; i++) {
			this.lugares.add(new Lugar((char) ('A' + i)));
		}
	}

	public int getId() {
		
		return id;
	}

	@Override
	public boolean isEmpty() {

		return lugares.stream().filter(lugar -> lugar.getReservaId() == 0).count() == lugares.size();
	}

	@Override
	public ArrayList<Character> getEmptyLugarIds() {

		ArrayList<Character> lugarIds = new ArrayList<>();
		for (Lugar lugar : lugares) {
			if (lugar.getReservaId() == 0)
				lugarIds.add(lugar.getId());
		}
		return lugarIds;
	}

	@Override
	public void setReservaIdOf(char lugarId, int reservaId) {
		
		for (Lugar lugar : lugares)
			if (lugar.getId() == lugarId)
				lugar.setReservaId(reservaId);
	}
	
	@Override
	public ArrayList<Character> getLugarIdsBy(int reservaId) {

		ArrayList<Character> lugarIds = new ArrayList<>();
		for (Lugar lugar : lugares)
			if (lugar.getReservaId() == reservaId)
				lugarIds.add(lugar.getId());
		return lugarIds;
	}
	
	@Override
	public int getReservaIdOf(char lugarId) {

		for (Lugar lugar : lugares)
			if (lugar.getId() == lugarId)
				return lugar.getReservaId();
		return 0; // Nunca executa porque lugar existe sempre
	}
	
	@Override
	public int getnLugares() {

		return lugares.size();
	}
}
