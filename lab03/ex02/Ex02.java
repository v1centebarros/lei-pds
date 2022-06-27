package lab03.ex02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Ex02 {
    static HashMap<String, Voos> voos = new HashMap<>();

    public static void menu(){
        System.out.println("-----------------------------------");
        System.out.println("H\n" +
                "\t-> Ajuda, apresenta as opções do menu \n" +
                "I filename\n" +
                "\t-> Ler Ficheiro de texto contendo informação sobre um voo\n" +
                "M flight_code \n" +
                "\t-> Mostrar mapa de reservas de um voo\n" +
                "F flight_code num_seats_executive num_seats_tourist\n" +
                "\t-> Acrescentar novo Voo com código, lugares em executiva\n" +
                "\t   e lugares em turistica\n" +
                "R flight_code class number_seats\n" +
                "\t-> Acrescentar uma nova reserva a um voo com a indicação da classe\n" +
                "\t   e do número de lugares \n" +
                "C reservation_code\n" +
                "\t-> Cancelar reserva\n" +
                "Q\n" +
                "\t-> Terminar programa\n");
        System.out.println("-----------------------------------");
    }
    public static void tratamentoOption(String comando, String[] options){
        switch (comando){
            case "H":
                if(options.length!=1){
                    System.err.println("Erro: Esta opção não aceita argumentos.");
                    System.out.println();
                    return;
                }
                menu();
                System.out.println();
                break;

            case "I":
                if(options.length!=2){
                    System.err.println("Usage: I <filename>");
                    System.out.println();
                    return;
                }
                String filename = options[1];
                readFile(filename);
                System.out.println();
                break;

            case "M":
                if(options.length!=2){
                    System.err.println("Usage: M <codigo_Voo>");
                    System.out.println();
                    return;
                }
                String codigo = options[1];
                Voos voo = voos.get(codigo);
                if (voo == null){
                    System.err.println("Código Do Voo Inexistente!");
                    System.out.println();
                    return;
                }
                voo.printMap();
                System.out.println();
                break;

            case "F":
                if(options.length!=3 && options.length != 4){
                    System.err.println("Usage: f <code_Voo> <num_seats_executive>(optional) <num_seats_tourist>");
                    System.out.println();
                    return;
                }
                novoVoo(options);
                System.out.println();
                break;

            case "R":
                if (options.length != 4) {
                    System.err.println("Usage: f <code_Voo> <Class> <number_seats>");
                    System.out.println();
                    return;
                }
                novaReserva(options);
                System.out.println("\n");
                break;

            case "C":
                if (options.length != 2) {
                    System.err.println("Usage: f <code_Voo:reservation_code>");
                    System.out.println();
                    return;
                }
                removeRes(options);
                System.out.println("Remover Reserva..........");
                System.out.println();
                break;

            case "Q":
                System.out.println("Terminar Programa..........");
                System.exit(0);
                break;

            default:
                System.err.println("ERRO: Invalid Option!");
                System.out.println();
        }
    }

    private static void removeRes(String[] info) {
        String[] options = info[1].split(":");
        String codigo = options[0];
        if (!(voos.containsKey(codigo))) {
            System.err.println("Este voo não existe!");
            return;
        }
        Voos voo = voos.get(codigo);
        int reservation_code;
        try{
            reservation_code= Integer.parseInt(options[1]);
        }catch (Exception e){
            System.err.println("Número de reserva inválido!");
            return;
        }
        boolean foundIt = voo.removeReserva(reservation_code);
        if (!foundIt) {
            System.err.println("Esta reserva não existe!");
        }
    }

    //funçao de leitura do ficheiro da opçao I
    private static void readFile(String filename) {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);
            String[] firstLine = sc.nextLine().split("\\s+");

            //ANÁLISE DA 1 LINHA
            //1-verificar o primeiro caracter da primiera linha e tem mais de 3 parametros
            if ((firstLine[0].charAt(0) != '>') || (firstLine.length > 3)) {
                System.err.println("Ficheiro Mal Formatado");
                return;
            }

            /*se a primeira linha tiver 3 paramatros,
             * 1ºcodigo, 2ºclasse executive e 3ºclasse turista,
             * Se não temos apenas codigo, e classe turistica*/

            String codigo = firstLine[0].substring(1); //extrair codigo do voo
            if (voos.containsKey(codigo)) {
                System.err.println("Código já existente!");
                return;
            }
            int numFilasE = 0, numLugaresE = 0;
            int indiceTur;

            //SE existir classe executiva
            if (firstLine.length == 3){
                String[] executive = firstLine[1].split("x");
                numFilasE = Integer.parseInt(executive[1]);
                numLugaresE = Integer.parseInt(executive[0]);
                indiceTur = 2;
            }else{
                indiceTur = 1;
            }

            //classe turistica
            int numFilasT, numLugaresT;
            String[] turistica = firstLine[indiceTur].split("x");
            numFilasT = Integer.parseInt(turistica[1]);
            numLugaresT = Integer.parseInt(turistica[0]);

            //criar aviao e voo
            int[][] exec = new int[numFilasE][numLugaresE];
            int[][] tur = new int[numFilasT][numLugaresT];
            Aviao aviao = new Aviao(exec, tur);
            Voos voo = new Voos(codigo, aviao);

            voos.put(codigo,voo);
            voo.printInfo(); // imprimir toda a informação
            //--------------------------------------------------------------
            //ANALISE DO RESTO DO FICHEIRO-------------------------------
            ArrayList<String> badReservas = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] info = line.split(" ");

                boolean isValid = addRes(codigo, info);

                if (!isValid && info.length == 2) // caso contrário, adicionamos à lista de reservas falhadas
                    badReservas.add(info[0] + " " + info[1]);

            }
            sc.close();
            System.out.print("Não foi possível obter lugares para a reserva: ");
            for (String badReserva : badReservas)
                System.out.println(badReserva + "; "); // imprimir reservas falhadas
            //-----------------------------------------------------------
        }catch (FileNotFoundException e){
            System.err.println("Ficheiro não encontrado!");
        }catch (NumberFormatException e) {
            System.err.println("Mau formato!");
        }
    }

    //funçao que cria um voo
    private static void novoVoo(String[] options){
        String codigo = options[1];
        try{
            if (voos.containsKey(codigo)) {
                System.err.println("Código já existente!");
                return;
            }
            int numFilasE = 0, numLugaresE = 0;
            int indiceTur;

            //SE existir classe executiva
            if (options.length == 4){
                String[] executive = options[2].split("x");
                numFilasE = Integer.parseInt(executive[1]);
                numLugaresE = Integer.parseInt(executive[0]);
                indiceTur = 3;
            }else{
                indiceTur = 2;
            }

            //classe turistica
            int numFilasT, numLugaresT;
            String[] turistica = options[indiceTur].split("x");
            numFilasT = Integer.parseInt(turistica[1]);
            numLugaresT = Integer.parseInt(turistica[0]);

            //criar aviao e voo
            int[][] exec = new int[numFilasE][numLugaresE];
            int[][] tur = new int[numFilasT][numLugaresT];
            Aviao aviao = new Aviao(exec, tur);
            Voos voo = new Voos(codigo, aviao);
            voos.put(codigo,voo);

        }catch (Exception e){
            System.err.println("Mau formato!");
        }

    }

    //funçao que faz uma nova reserva
    private static void novaReserva(String[] options) {
        String codigo = options[1];
        boolean isValid = addRes(codigo, new String[]{options[2], options[3]});
        if (!isValid){
            System.err.println("Não é possivel colocar a reserva!");
            return;
        }
        Voos voo = voos.get(codigo);

        Classe classe = criarClasse(options[2]);
        if(classe == Classe.NONE){return;}

        System.out.print(codigo + " : " + voo.getReserva() + " = ");
        voo.refBanco(voo.getReserva(), classe, Integer.parseInt(options[3]));

    }

    //funçao de validaçao de uma reserva
    private static boolean addRes(String codigo, String[] infoRes) {
        Classe classe = criarClasse(infoRes[0]);
        if(classe == Classe.NONE){return false;}
        boolean madeRes = false;

        if (voos.containsKey(codigo))
            madeRes = voos.get(codigo).reservaAdd(Integer.parseInt(infoRes[1]), classe);
        return madeRes;
    }

    private static Classe criarClasse(String option) {
        Classe classe;

        if (option.equals("E")) {
            classe = Classe.EXECUTIVA;
        }
        else if (option.equals("T")) {
            classe = Classe.TURISTICA;
        }
        else {
            System.err.println("Classe não existe!");
            classe = Classe.NONE;
        }
        return classe;
    }

    public static void main(String[] args)  {
        //validação de um ficheiro de comandos
        Scanner sc;
        File comandos;
        boolean lerFile = false;
        if (args.length < 1){
            menu();
            sc = new Scanner(System.in);
        }
        else if (args.length == 1){
            lerFile = true;
            comandos = new File(args[0]);
            try{
                sc = new Scanner(comandos);
            }catch (FileNotFoundException e) {
                System.err.println("Erro: Ficheiro de comandos não encontrado!");
                return;
            }
        } else{
            System.err.println("Erro: Aceitar Ficheiro de comandos ou nenhum argumento!");
            return;
        }

        String[] options; //array para guardar a informação das opções

        while (true){
            if (!lerFile){
                System.out.println("Escolha uma opção: (H para ajuda)");
            }
            options = sc.nextLine().split("\\s+");
            String command = options[0].toUpperCase();
            if(args.length == 1){
                for (int i = 0; i< options.length; i++)
                    System.out.print(options[i] + " ");
                System.out.println();
            }
            tratamentoOption(command,options);
        }
    }
}
