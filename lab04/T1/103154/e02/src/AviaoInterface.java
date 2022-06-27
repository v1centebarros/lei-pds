import java.util.ArrayList;

public interface AviaoInterface {

	//
	// Voo: +createReserva()
	//
	ArrayList<Integer> getEmptyFilaIdsBy(ClasseType classe);

	//
	// Voo: +createReserva()
	//
	ArrayList<String> getEmptyFilaLugarIdsBy(ClasseType classe);

	//
	// Voo: +createReserva()
	//
	void setReservaIdOf(String lugarFilaId, int reservaId);

	//
	// Voo: +cancelReserva()
	//
	ArrayList<String> getFilaLugarIdsBy(int reservaId);
	
	//
	// Voo: +printMapOfReservas()
	//
	int getnFilasBy(ClasseType classe);

	//
	// Voo: +printMapOfReservas()
	//
	int getnLugaresFilaBy(ClasseType classe);
	
	//
	// Voo: +printMapOfReservas()
	//
	int getReservaIdOf(String lugarFilaId);
	
	//
	// Voo: +toString()
	//
	boolean hasExecutiva();

	//
	// Voo: +toString()
	//
	int getnLugaresBy(ClasseType classe);
}
