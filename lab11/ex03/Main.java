package lab11.ex03;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Book> library = new ArrayList<>();

        library.add(new Book("Java Anti-Stress", "Omodionah"));
        library.add(new Book("A Guerra dos Padrões", "Jorge Omel"));
        library.add(new Book("A Procura da Luz", "Khumatkli"));

        boolean flag = true;
        int id, op;
        Book book;

        while (true) {
            if (flag) printLibrary(library);

            System.out.print(">>");
            String[] input = sc.nextLine().split(",");

            try {
                id = Integer.parseInt(input[0])-1;
                op = Integer.parseInt(input[1]);
                book = library.get(id);

            } catch (Exception e) {
                System.out.println("ERRO! Input inválido");
                flag = false;
                continue;
            }
            try {
                switch (op) {
                    case 1 -> book.regista();
                    case 2 -> book.requisita();
                    case 3 -> book.devolve();
                    case 4 -> book.reserva();
                    case 5 -> book.cancelaReserva();
                    default -> System.out.println("Operação inválida");
                }
                flag = true;
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage()+"\n");
                flag = false;
            }
        }

    }

    public static void printLibrary(ArrayList<Book> books) {
        System.out.println("*** Biblioteca ***");

        for (int i=1; i<=books.size(); i++) {
            System.out.println(i+"\t"+books.get(i-1));
        }

        System.out.println(">> <livro>, <operação: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela");
    }
}
