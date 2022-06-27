import java.util.ArrayList;

public interface FilaInterface {
	//
	// Aviao: +getEmptyFilaIdsBy()
	//
	boolean isEmpty();
	
	//
	// Aviao: +getEmptyFilaLugarIdsBy()
	//
	ArrayList<Character> getEmptyLugarIds();
	
	//
	// Aviao: +setReservaIdOf()
	//
	void setReservaIdOf(char lugarId, int reservaId);
	
	//
	// Aviao: +getFilaLugarIdsBy()
	//
	ArrayList<Character> getLugarIdsBy(int reservaId);
	
	//
	// Aviao: +getReservaIdOf()
	//
	int getReservaIdOf(char lugarId);
	
	//
	// Aviao: +getnLugaresBy()
	//
	int getnLugares();
	
}
