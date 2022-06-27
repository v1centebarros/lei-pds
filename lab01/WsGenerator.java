package lab01;

import java.io.*;
import java.util.*;

public class WsGenerator {
    static int MaxSize = 40; //tamanho maximo da sopa definido
    static ArrayList<String> words; //lista que guardar as palavras
    static char[][] sopa; //array que vai guardar a sopa

    //funçao que carrega as palavra que vao gerar à sopa de letras
    static void readFile(File file,  int sizeMatrix) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            //no caso de uma das linhas estar esteja vazia
            if (line.isEmpty()) {
                System.out.println("ERROR: LINHA VAZIA");
                System.exit(1);
            }
            // Palavra está em maiúsculas
            if (line.equals(line.toUpperCase())) {
                System.err.println("ERROR: PALAVRA INVALIDA. Palavras devem estar só em minúsculas ou misturadas. ");
                System.exit(1);
            }

            String[] currentWords = line.split("[,; ]");
            for (int i = 0; i < currentWords.length; i++) {
                if(currentWords[i].length() > sizeMatrix){
                    System.err.println("ERROR: DIMENSÃO DA SOPA INCOMPATIVEL COM PELO MENOS UMA DAS PALAVRAS");
                    System.exit(1);
                }
                for (int k = 0; k < currentWords[i].length(); k++){
                    //verificar se as palavras contem apenas carcteres alfabeticos
                    if (!Character.isAlphabetic(currentWords[i].charAt(k))) {
                        System.err.println("ERROR: CARACTER TEM DE SER ALFABETICO");
                        System.exit(1);
                    }
                }
                //verificar se nao palavras duplicadas, para nao gerar 2 possiveis soluções
                if (words.contains(currentWords[i])) {
                    System.err.printf("Erro: A PALAVRAS NÃO PODEM ESTAR DUPLICADAS (%s) \n", currentWords[i]);
                    System.exit(1);
                }
                words.add(currentWords[i].toUpperCase());
            }
        }
        System.out.println("Palavras a inserir: " + words);
        scanner.close();
    }

    //funçao que gera a sopa de letras aletoriamente
    static void generate(int sizeMatrix){

        // Ordenar palavras por ordem decrescente de comprimento
        ArrayList<String> descentWords = new ArrayList<>();
        descentWords.addAll(words);
        Collections.sort(descentWords, Comparator.comparingInt(String::length).reversed());

        Random rand = new Random();
        int x, y, direction, width;
        boolean notclear;
        boolean erro = true;

        for (int i = 0; i < descentWords.size(); i++) {
            String word = descentWords.get(i);
            if (erro) {
                System.out.printf("%-15s", word);
            }
            width = word.length();
            if (erro) {
                System.out.printf("%-10d", width);
            }
            direction = rand.nextInt(8); // Returns integer between [0, arg-1]
            switch (direction) {
                case 0:
                    if (erro) {
                        System.out.printf("%-15s", "RIGHT");
                    }
                    do {
                        notclear = false;
                        x = rand.nextInt(sizeMatrix);
                        y = rand.nextInt(sizeMatrix - width);

                        for (int col = y; col < y + width; col++) {
                            /* verifca se na posiçao em questão esta vazia ou caracter igual ao que se pretende colocar
                            * posiçao anterior a que vamos colocar esta situada fora da sopa, ou se esta vazia
                            * posiçao final da palavra fica de fora da margem ou se nao esta vazia*/
                            if (sopa[x][col] != '\0' && sopa[x][col] != word.charAt(col - y)
                                    || col == y && col - 1 != -1 && sopa[x][col - 1] != '\0'
                                    || col == y + width - 1 && col + 1 != sizeMatrix && sopa[x][col + 1] != '\0') {
                                notclear = true;
                                break;
                            }
                        }
                    } while (notclear);
                    if (erro) {
                        System.out.printf("%-15s", (x + 1) + ", " + (y + 1));
                    }
                    for (int col = y; col < y + width; col++) {
                        sopa[x][col] = word.charAt(col - y);
                    }
                    break;
                case 1:
                    if (erro) {
                        System.out.printf("%-15s", "LEFT");
                    }
                    do {
                        notclear = false;
                        x = rand.nextInt(sizeMatrix);
                        y = rand.nextInt(sizeMatrix - width) + width;
                        for (int col = y; col > y - width; col--) {
                            if (sopa[x][col] != '\0' && sopa[x][col] != word.charAt(y - col)
                                    || col == y && col + 1 != sizeMatrix && sopa[x][col + 1] != '\0'
                                    || col == y - width + 1 && col - 1 != -1 && col - 1 != 0 && sopa[x][col - 1] != '\0') {
                                notclear = true;
                                break;
                            }
                        }
                    } while (notclear);
                    if (erro) {
                        System.out.printf("%-15s", (x + 1) + ", " + (y + 1));
                    }
                    for (int col = y; col > y - width; col--) {
                        sopa[x][col] = word.charAt(y - col);
                    }
                    break;
                case 2:
                    if (erro) {
                        System.out.printf("%-15s", "DOWN");
                    }
                    do {
                        notclear = false;
                        x = rand.nextInt(sizeMatrix - width);
                        y = rand.nextInt(sizeMatrix);
                        for (int row = x; row < x + width; row++) {
                            if (sopa[row][y] != '\0' && sopa[row][y] != word.charAt(row - x)
                                    || row == x && row - 1 != -1 && sopa[row - 1][y] != '\0'
                                    || row == x + width - 1 && row + 1 != sizeMatrix && sopa[row + 1][y] != '\0') {
                                notclear = true;
                                break;
                            }
                        }
                    } while (notclear);
                    if (erro) {
                        System.out.printf("%-15s", (x + 1) + ", " + (y + 1));
                    }
                    for (int row = x; row < x + width; row++) {
                        sopa[row][y] = word.charAt(row - x);
                    }
                    break;
                case 3:
                    if (erro) {
                        System.out.printf("%-15s", "UP");
                    }
                    do {
                        notclear = false;
                        x = rand.nextInt(sizeMatrix - width) + width;
                        y = rand.nextInt(sizeMatrix);
                        for (int row = x; row > x - width; row--) {
                            if (sopa[row][y] != '\0' && sopa[row][y] != word.charAt(x - row)
                                    || row == x && row + 1 != sizeMatrix && sopa[row + 1][y] != '\0'
                                    || row == x - width + 1 && row - 1 != -1 && sopa[row - 1][y] != '\0') {
                                notclear = true;
                                break;
                            }
                        }
                    } while (notclear);
                    if (erro) {
                        System.out.printf("%-15s", (x+1)+ ", " + (y+1));
                    }
                    for (int row = x; row > x - width; row--) {
                        sopa[row][y] = word.charAt(x - row);
                    }
                    break;
                case 4:
                    int row;
                    if (erro) {
                        System.out.printf("%-15s", "UPRIGHT");
                    }
                    do {
                        notclear = false;
                        x = rand.nextInt(sizeMatrix - width) + width;
                        y = rand.nextInt(sizeMatrix - width);
                        row = x;
                        for (int col = y; col < y + width; col++) {
                            if (sopa[row][col] != '\0' && sopa[row][col] != word.charAt(col - y)
                                    || col == y &&  row == x && col - 1 != -1 && row + 1 != sizeMatrix && sopa[row + 1][col - 1] != '\0'
                                    || col == y + width - 1 && row == x - width + 1 && row - 1 != -1 && col + 1 != sizeMatrix && sopa[row -1 ][col + 1] != '\0'){
                                notclear = true;
                                break;
                            }
                            row--;
                        }
                    } while (notclear);
                    if (erro) {
                        System.out.printf("%-15s", (x + 1) + ", " + (y + 1));
                    }

                    row = x;
                    for (int col = y; col < y + width; col++) {
                        sopa[row][col] = word.charAt(col - y);
                        row--;
                    }
                    break;
                case 5:
                    if (erro) {
                        System.out.printf("%-15s", "UPLEFT");
                    }
                    do {
                        notclear = false;
                        x = rand.nextInt(sizeMatrix - width) + width;
                        y = rand.nextInt(sizeMatrix - width) + width;
                        row = x;
                        for (int col = y; col > y - width; col--) {
                            if (sopa[row][col] != '\0' && sopa[row][col] != word.charAt(y - col)
                                    || col == y && row == x && col + 1 != sizeMatrix && row + 1 != sizeMatrix && sopa[row + 1 ][col + 1] != '\0'
                                    || col == y - width + 1 && row == x - width + 1 && col - 1 != -1 && row - 1 != -1 && col - 1 != 0 && sopa[row - 1][col - 1] != '\0'){
                                notclear = true;
                                break;
                            }
                            row--;
                        }
                    } while (notclear);
                    if (erro) {
                        System.out.printf("%-15s", (x + 1) + ", " + (y + 1));
                    }
                    row = x;
                    for (int col = y; col > y - width; col--) {
                        sopa[row][col] = word.charAt(y - col);
                        row--;
                    }
                    break;
                case 6:
                    if (erro) {
                        System.out.printf("%-15s", "DOWNRIGHT");
                    }
                    do {
                        notclear = false;
                        x = rand.nextInt(sizeMatrix - width);
                        y = rand.nextInt(sizeMatrix - width);
                        row = x;
                        for (int col = y; col < y + width; col++) {
                            if (sopa[row][col] != '\0' && sopa[row][col] != word.charAt(col - y)
                                    || col == y && row == x && col - 1 != -1 && row - 1 != -1 && sopa[row - 1][col - 1] != '\0'
                                    || col == y + width - 1 && row == x + width - 1 && col + 1 != sizeMatrix && row + 1 != sizeMatrix && sopa[row + 1][col + 1] != '\0'){
                                notclear = true;
                                break;
                            }
                            row++;
                        }
                    } while (notclear);
                    if (erro) {
                        System.out.printf("%-15s", (x + 1) + ", " + (y + 1));
                    }
                    row = x;
                    for (int col = y; col < y + width; col++) {
                        sopa[row][col] = word.charAt(col - y);
                        row++;
                    }
                    break;
                case 7:
                    if (erro) {
                        System.out.printf("%-15s", "DOWNLEFT");
                    }
                    do {
                        notclear = false;
                        x = rand.nextInt(sizeMatrix - width);
                        y = rand.nextInt(sizeMatrix - width) + width;
                        row = x;
                        for (int col = y; col > y - width; col--) {
                            if (sopa[row][col] != '\0' && sopa[row][col] != word.charAt(y - col)
                                    || col == y && row == x && col + 1 != sizeMatrix && row - 1 != -1 && sopa[row - 1][col + 1] != '\0'
                                    || col == y - width + 1 && row == x + width - 1 && col - 1 != -1 && row + 1 != sizeMatrix && col - 1 != 0 && sopa[row + 1][col - 1] != '\0'){
                                notclear = true;
                                break;
                            }
                            row++;
                        }
                    } while (notclear);
                    if (erro) {
                        System.out.printf("%-15s", (x + 1) + ", " + (y + 1));
                    }
                    row = x;
                    for (int col = y; col > y - width; col--) {
                        sopa[row][col] = word.charAt(y - col);
                        row++;
                    }
                    break;

            }
            if (erro) {
                System.out.printf("%s\n", "DONE!");
            }
        }

    }
    //funçao que imprime a sopa e as palavras para o ficheiro
    static void imprimir(int sizeMatrix,FileWriter file_writer) throws IOException {
        BufferedWriter bw = new BufferedWriter(file_writer);
        PrintWriter printWriter =new PrintWriter(bw);

        for (int j = 0; j < sizeMatrix; j++) {
            for (int k = 0; k < sizeMatrix; k++) {
                printWriter.printf("%c", sopa[j][k]);
            }
            printWriter.println();
        }
        for (String word : words) {
            printWriter.printf("%s\n", word.toLowerCase());
        }
        printWriter.close();

    }

    public static void main(String[] args) throws IOException {

        //sintaxe do programa
        if (args.length < 6) {
            System.out.println("Usage: java WSGenerator  -i <file> -s <size> [opções]");
            System.err.println("options: -o [output_file_path]");
            System.exit(1);
        }

        int sizeMatrix = 0;
        boolean[] flags = new boolean[3]; // [ i_flag, s_flag, o_flag ]
        File file = null;
        FileWriter outfile = null;

        //tratamento de argumentos
        for (int i = 0; i < args.length - 1; i= i + 2) {
            switch (args[i]) {
                case "-i":
                    flags[0] = true;
                    System.out.println(args[i +1]);
                    file = new File(args[i + 1]);
                    break;
                case "-s":
                    flags[1] = true;
                    sizeMatrix = Integer.parseInt(args[i + 1]);

                    if (sizeMatrix > MaxSize) {
                        System.out.printf("ERROR: TAMANHO DA SOPA (>%d)\n", MaxSize);
                        System.exit(1);
                    }
                    break;
                case "-o":
                    flags[2] = true;
                    outfile = new FileWriter(args[i + 1]);
                    break;
                default:
                    System.err.printf("Erro: Opção inválida \"%s\".\n", args[i]);
                    System.exit(1);
            }
        }

        // Não foram fornecidas as opçãoes obrigatórias: -i, -s
        if (!flags[0] || !flags[1] || !flags[2])
        {
            System.err.println("Erro: Obrigatório fornecer as opções \"-i [wordlist_path]\" e \"-s [size]\"e \"-o [out_path]\".");
            System.exit(1);
        }

        /*inicializar o puzzle com o tamanho predefinido
        vai ser sempre um quadrado porque é usado o mesmo valor (sizeMatrix) para as linhas e
        colunas, ou seja, é sempre sizeMatrix* sizeMatrix */
        sopa = new char[sizeMatrix][sizeMatrix];

        readFile(file, sizeMatrix);

        boolean solutionFind = false;
        do{
            WSSolverClass temp = new WSSolverClass();
            for (int j = 0; j < sizeMatrix; j++) {
                for (int k = 0; k < sizeMatrix; k++) {
                    sopa[j][k] = '\0';
                }
            }
            // Generate word search
            generate(sizeMatrix);
            for (int j = 0; j < sizeMatrix; j++) {
                for (int k = 0; k < sizeMatrix; k++) {
                    if (sopa[j][k] == '\0') {
                        sopa[j][k] = getRandChar();
                    }
                }
            }
            //Imprimir dados para o ficheiro
            imprimir(sizeMatrix,outfile);
            System.out.println("-----------------------");
            solutionFind=temp.solve(sopa,words);

        }while(!solutionFind);
    }

    //funçao que gera um caracter aletorio
    private static char getRandChar(){
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            Random r = new Random();
            return alphabet.charAt(r.nextInt(alphabet.length()));
    }
}
