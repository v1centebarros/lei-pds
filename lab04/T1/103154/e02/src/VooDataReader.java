import java.io.*;
import java.util.*;

public class VooDataReader implements VooDataReaderInterface {

	private Scanner sc;
	private String codigoVoo;
	private int nFilasE;
	private int nLugaresFilaE;
	private int nFilasT;
	private int nLugaresFilaT;

	private VooDataReader(Scanner sc, String codigoVoo, int nFilasE, int nLugaresFilaE, int nFilasT, int nLugaresFilaT) {
		this.sc = sc;
		this.codigoVoo = codigoVoo;
		this.nFilasE = nFilasE;
		this.nLugaresFilaE = nLugaresFilaE;
		this.nFilasT = nFilasT;
		this.nLugaresFilaT = nLugaresFilaT;
	}

	public static VooDataReader getInstanceFromFile(String filePath) {

		Scanner sc = null;
		String codigoVoo = null;
		int nFilasE = 0;
		int nLugaresFilaE = 0;
		int nFilasT = 0;
		int nLugaresFilaT = 0;

		// Tentar abrir ficheiro e scanner respetivo:
		try {
			sc = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			System.err.println("Erro: Ficheiro não foi encontrado");
			return null;
		}

		// Ficheiro vazio:
		if (!sc.hasNextLine()) {
			System.err.println("Erro: Ficheiro está vazio");
			sc.close();
			return null;
		}

		codigoVoo = sc.next();
		// Primeira linha não contém '>' como primeiro caractere:
		if (codigoVoo.charAt(0) != '>') {
			System.err.println("Erro: Primeiro caractere do ficheiro deve ser \"%s\"");
			sc.close();
			return null;
		}
		codigoVoo = codigoVoo.replaceFirst(">", "");

		String[] dims = sc.nextLine().trim().split("[ ]+"); // Dimensões de todas as classes em String
		String[] split; // split[0] - nFilas, split[1] - nLugares

		// Fornecida apenas classe turística:
		if (dims.length == 1) {
			split = dims[0].split("x");
			nFilasT = Integer.parseInt(split[0]);
			nLugaresFilaT = Integer.parseInt(split[1]);
		}
		// Fornecidas classe executiva e classe turística
		else {
			split = dims[0].split("x"); // executiva
			nFilasE = Integer.parseInt(split[0]);
			nLugaresFilaE = Integer.parseInt(split[1]);
			split = dims[1].split("x"); // turística
			nFilasT = Integer.parseInt(split[0]);
			nLugaresFilaT = Integer.parseInt(split[1]);
		}

		return new VooDataReader(sc, codigoVoo, nFilasE, nLugaresFilaE, nFilasT, nLugaresFilaT);
	}

	public static VooDataReader getInstanceFromInput(String line) {

		Scanner sc = null;
		String codigoVoo = null;
		int nFilasE = 0;
		int nLugaresFilaE = 0;
		int nFilasT = 0;
		int nLugaresFilaT = 0;

		sc = new Scanner(line);

		codigoVoo = sc.next();
		// Primeira linha não contém '>' como primeiro caractere:
		if (codigoVoo.charAt(0) != '>') {
			System.err.println("Erro: Primeiro caractere do ficheiro deve ser \"%s\"");
			sc.close();
			return null;
		}

		String[] dims = sc.nextLine().trim().split("[ ]+"); // Dimensões de todas as classes em String
		String[] split; // split[0] - nFilas, split[1] - nLugares

		// Fornecida apenas classe turística:
		if (dims.length == 1) {
			split = dims[0].split("x");
			nFilasT = Integer.parseInt(split[0]);
			nLugaresFilaT = Integer.parseInt(split[1]);
		}
		// Fornecidas classe executiva e classe turística
		else {
			split = dims[0].split("x"); // executiva
			nFilasE = Integer.parseInt(split[0]);
			nLugaresFilaE = Integer.parseInt(split[1]);
			split = dims[1].split("x"); // turística
			nFilasT = Integer.parseInt(split[0]);
			nLugaresFilaT = Integer.parseInt(split[1]);
		}

		sc.close();
		return new VooDataReader(null, codigoVoo, nFilasE, nLugaresFilaE, nFilasT, nLugaresFilaT);
	}

	@Override
	public String getCodigoVoo() {
		return codigoVoo;
	}

	@Override
	public int getnFilasE() {
		return nFilasE;
	}

	@Override
	public int getnLugaresFilasE() {
		return nLugaresFilaE;
	}

	@Override
	public int getnFilasT() {
		return nFilasT;
	}

	@Override
	public int getnLugaresFilasT() {
		return nLugaresFilaT;
	}

	@Override
	public Reserva getNextReserva() {

		Reserva reserva = null;

		// Não existem reservas:
		if (sc == null)
			return reserva;

		if (sc.hasNextLine()) {
			ClasseType classe = ClasseType.valueOf(sc.next());
			int nPassageiros = sc.nextInt();
			sc.nextLine();
			
			reserva = new Reserva(0, classe, nPassageiros); // Id da reserva é 0 porque ainda não foi validada neste voo
			
		} else {
			sc.close();
			sc = null;
		}
		return reserva;
	}

}
