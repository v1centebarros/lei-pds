import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {

		HashMap<String, Voo> voos = new HashMap<>();
		Scanner sc = null;

		// Comandos de um ficheiro:
		if (args.length > 0) {
			try {
				sc = new Scanner(new File(args[0]));
			} catch (FileNotFoundException e) {
				System.err.println("Erro: Ficheiro de comandos não foi encontrado");
				System.exit(1);
			}
		}
		// Comandos da consola:
		else {
			sc = new Scanner(System.in);
		}

		// Leitura de comandos:
		while (true) {
			System.out.println("Escolha uma opção: (H para ajuda)");

			if (!sc.hasNextLine())
				break;
			
			// Comando:
			switch (sc.next()) {
			case "H":
				printHelp();
				break;
			case "I":
				// Obter dados do ficheiro:
				VooDataReader vdr = VooDataReader.getInstanceFromFile(sc.next());
				// Criar voo:
				Voo voo = new Voo(vdr.getCodigoVoo(), vdr.getnFilasE(), vdr.getnLugaresFilasE(), vdr.getnFilasT(), vdr.getnLugaresFilasT());
				// Imprimir dados do voo:
				System.out.println(voo.toString());
				// Atribuir as reservas ao voo:
				Reserva reserva;
				while ((reserva = vdr.getNextReserva()) != null) {
					voo.createReserva(reserva.getClasse(), reserva.getnPassageiros());
				}
				break;
			case "M":
				// Imprimir mapa das reservas:
				voos.get(sc.next()).printMapOfReservas();
				break;
			case "F":
				break;
			case "R":
				break;
			case "C":
				break;
			case "Q":
				System.exit(0);
				break;
			default:
				System.err.println("Erro: Comando inválido");
			}
		}
		sc.close();
	}

	private static void printHelp() {
		System.out.println("H - Este menu");
		System.out.println("I filename - Lê ficheiro com informação sobre um voo");
		System.out.println("M flight_code - Mostra reservas de um voo");
		System.out.println("F flight_code num_seats_executive num_seats_tourist - Cria um novo voo");
		System.out.println("R flight_code class number_seats - Cria uma reserva num voo");
		System.out.println("C reservation_code - Cancela uma reserva");
		System.out.println("Q - Termina o programa");
		System.out.println();
	}

}
