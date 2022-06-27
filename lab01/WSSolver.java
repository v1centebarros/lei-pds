package lab01;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WSSolver {
    static int MAX_SIZE = 40;
    static String[][] matrix;
    static String[][] solutionMatrix;
    static List<String> words = new ArrayList<>();
    static List<Word> foundWords = new ArrayList<>();
    static List<Word> repeatedWords = new ArrayList<>();

    //funçao de verificacao se a palavra esta em maiusculas
    static boolean isUpper( String[] line) {
        for (String s : line){
            if(!Character.isUpperCase(s.charAt(0))) {
                return false;
            }
        }
        return true;
    }

    static Scanner startfileScanner (String filename) {
        try{
            return new Scanner(new File(filename));
        } catch (IOException e) {
            System.err.print("ERRO:Na leitura do Ficheiro");
            System.err.print(e);
            System.exit(1);
        }
        return null;
    }

    //funçao de construçao da Sopa
    static boolean readMatrix (Scanner sc) {
        String[] firstLine = sc.nextLine().split("");

        //verificacao se a sopa tem dimensoes corretas
        if (firstLine.length > MAX_SIZE || firstLine.length < 1){
            System.err.print("ERRO: Tamanho da sopa de letras Invalido (entre 1 e 40).");
            return false;
        }

        matrix = new String[firstLine.length][firstLine.length]; //matriz toma o tamanho da primeira linha
        matrix[0] = firstLine;
        String[] line;
        for (int i = 1; i < firstLine.length; i++) {
            line = sc.nextLine().split("");
            //verificaçao se a linha é do mesmo tamanho (matriz deve ser quadrada)
            if (firstLine.length != line.length) {
                System.err.print("ERRO: A sopa deve ser quadrada.");
                return false;
            }
            // verificaçao se esta escrita em maiusculas
            if (!isUpper(line)){
                System.err.print("ERRO: A sopa deve ser dada em Maiusculas.");
                return false;
            }
            matrix[i] = line;
        }
        return true;
    }

    //funçao de guardar as palavras para procurar na sopa
    static boolean readWords (Scanner sc) {
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            //verificaçao se a linha esta vazia
            //verificaçao se a linha nao pertence à sopa (matriz deve ser quadrada)
            if (line.trim().isEmpty() || line.matches("[A-Z ,;]+") ){
                System.err.print("ERRO: linha vazia ou a Sopa tem de ser quadadrada (linha a mais).");
                return false;
            }

            String[] lineData = line.split("[,; ]");

            for (String s : lineData) {
                //verificaçao se a palavra so tem caracteres alfabeticos
                if (!s.matches("[a-zA-Z]+")) {
                    System.err.print("ERRO: Palavras só tem caracteres alfabeticos.");
                    return false;
                }
                //verificaçao de palavras duplicadas
                if(words.contains(s)){
                    System.err.print("ERRO: A sopa só pode ter uma solução para cada palavra.");
                    return false;}
                words.add(s);
            }
        }
        return true;
    }

    //----------------------------funções de procura em todos os sentidos------------------------------
    static boolean rightSearch (String word,int line, int column){
        if (column + word.length() <= matrix[line].length) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line][i+column].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    static boolean leftSearch (String word,int line, int column){
        if (column+1 - word.length() >= 0) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line][column-i].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    static boolean downSearch (String word,int line, int column){
        if (line + word.length() <= matrix[line].length) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[i+line][column].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    static boolean upSearch (String word,int line, int column){
        if ((line - word.length()+1) >= 0) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line-i][column].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    static boolean downRightSearch (String word,int line, int column){
        if (column + word.length() <= matrix[line].length && line + word.length() <= matrix[line].length) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line+i][i+column].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    static boolean upRightSearch (String word,int line, int column){
        if (column + word.length() <= matrix[line].length && (line - word.length()+1)  >= 0) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line-i][i+column].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    static boolean downLeftSearch (String word,int line, int column){
        if (column+1 - word.length() >= 0 && line + word.length() <= matrix[line].length) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line+i][column-i].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    static boolean upLeftSearch (String word,int line, int column){
        if (column+1 - word.length() >= 0 && (line - word.length()+1) >= 0) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line-i][column-i].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }
    //---------------------------------------------------------------------------

    //procura de palavra na sopa
    static void findWord (String word) {
        String upWord = word.toUpperCase();
        int i,j, flag = 0;
        List<Word> tempList = new ArrayList<>();
        Word w;

        for (i = 0; i < matrix[0].length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j].charAt(0) == upWord.charAt(0)) {
                    if (rightSearch(upWord,i,j)) {
                        w = new Word(word,i,j,Direction.RIGHT);
                        foundWords.add(w);
                        tempList.add(w);
                        flag++;
                    }
                    if (leftSearch(upWord,i,j)) {
                        w = new Word(word,i,j,Direction.LEFT);
                        foundWords.add(w);
                        tempList.add(w);
                        flag++;
                    }
                    if (downSearch(upWord,i,j)) {
                        w = new Word(word,i,j,Direction.DOWN);
                        foundWords.add(w);
                        tempList.add(w);
                        flag++;
                    }
                    if (upSearch(upWord,i,j)) {
                        w = new Word(word,i,j,Direction.UP);
                        foundWords.add(w);
                        tempList.add(w);
                        flag++;
                    }
                    if (downRightSearch(upWord,i,j)) {
                        w = new Word(word,i,j,Direction.DOWNRIGHT);
                        foundWords.add(w);
                        tempList.add(w);
                        flag++;
                    }
                    if (downLeftSearch(upWord,i,j)) {
                        w = new Word(word,i,j,Direction.DOWNLEFT);
                        foundWords.add(w);
                        tempList.add(w);
                        flag++;
                    }
                    if (upRightSearch(upWord,i,j)) {
                        w = new Word(word,i,j,Direction.UPRIGHT);
                        foundWords.add(w);
                        tempList.add(w);
                        flag++;
                    }
                    if (upLeftSearch(upWord,i,j)) {
                        w = new Word(word,i,j,Direction.UPLEFT);
                        foundWords.add(w);
                        tempList.add(w);
                        flag++;
                    }
                }
            }
        }
        if (flag > 1)
            repeatedWords.addAll(tempList);

        if (flag == 0)
            foundWords.add(new Word(word,-1,-1,Direction.UNKNOWN));

    }

    //----------------------funções de escrever na matriz soluçao de acordo com a soluçao---------------
    static void rightPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line][i + column] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    static void leftPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line][column-i] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    static void downPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line+i][column] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    static void upPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line - i][column] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    static void downRightPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line + i][column + i] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    static void upRightPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line-i][i + column] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    static void downLeftPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line+i][column-i] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    static void upLeftPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line-i][column-i] = String.valueOf(word.charAt(i)).toUpperCase();
    }
    //-----------------------------------------------------------------------------------

    //funçao de escrever a soluçao na matriz de acordo com a direçao
    static void writeWordInSolution (Word w) {
        switch (w.getDirection()) {
            case RIGHT:
                rightPrint(w.getWord(),w.getLine(),w.getColumn());
                break;
            case LEFT:
                leftPrint(w.getWord(),w.getLine(),w.getColumn());
                break;
            case DOWN:
                downPrint(w.getWord(),w.getLine(),w.getColumn());
                break;
            case UP:
                upPrint(w.getWord(),w.getLine(),w.getColumn());
                break;
            case DOWNRIGHT:
                downRightPrint(w.getWord(),w.getLine(),w.getColumn());
                break;
            case DOWNLEFT:
                downLeftPrint(w.getWord(),w.getLine(),w.getColumn());
                break;
            case UPRIGHT:
                upRightPrint(w.getWord(),w.getLine(),w.getColumn());
                break;
            case UPLEFT:
                upLeftPrint(w.getWord(),w.getLine(),w.getColumn());
                break;
        }
    }

    //funçao de verificaçao de colisoes
    static boolean checkColisions () {
        ArrayList <Word> tmp = new ArrayList<>(foundWords);

        for (Word rWord : repeatedWords) {
            for (Word fWord : foundWords){

                if (rWord.getDirection().getOrientation() == fWord.getDirection().getOrientation() && !fWord.getWord().equals(rWord.getWord()) && fWord.getWord().contains(rWord.getWord())){
                    tmp.remove(rWord);
                }
            }
        }
        foundWords = new ArrayList<>(tmp);

        return  words.size() == foundWords.size();
    }

    public static void main(String[] args) {
        Scanner sc = startfileScanner(args[0]);

        //verificar se o ficheiro tem linhas vazias
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                System.err.println("Erro ao ler o Ficheiro");
                System.exit(1);
            }
        }
        sc.close();
        sc = startfileScanner(args[0]);

        //Ler sopa de letras
        if (!readMatrix(sc)) {
            System.err.println("Erro ao Ler a Sopa de Letras");
            System.exit(1);
        }
        //Ler palavras do ficheiro
        if (!readWords(sc)) {
            System.err.println("Erro ao Ler as Palavras dos ficheiros");
            System.exit(1);
        }
        //Iniciar a matrix solução com "."
        solutionMatrix = new String[matrix[0].length][matrix[0].length];
        for (String[] line : solutionMatrix) Arrays.fill(line,".");

        //Procurar as palavras
        for (String word: words) findWord(word);

        //Verificaçao de colisoes
        if(!checkColisions()){
            System.err.print("ERRO: Colisão de soluções");
            System.err.println("ERRO! NA RESOLUÇÃO DA SOPA DE LETRAS!");
            System.exit(1);
        }
        //Palavras sem solução
        for (Word w : foundWords) {
            if(w.getDirection().getOrientation()=="UN"){
                System.err.print("ERRO: Cada palavra tem de ter uma solução na sopa.");
                System.err.println("ERRO! NA RESOLUÇÃO DA SOPA DE LETRAS!");
                System.exit(1);
            }
        }
        //Escrever na matrix solução
        for (Word w : foundWords) {
            writeWordInSolution(w);
            System.out.print(w);
        }
        System.out.println();

        //Mostrar a solução
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(solutionMatrix[i][j] + " ");
            }
            System.out.print("\n");
        }

    }
}