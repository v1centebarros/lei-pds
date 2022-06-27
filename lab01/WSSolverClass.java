package lab01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WSSolverClass {
    private final int MAX_SIZE = 40;
    private String[][] matrix;
    private String[][] solutionMatrix;
    private List<String> words = new ArrayList<>();
    private List<Word> foundWords = new ArrayList<>();
    private List<Word> repeatedWords = new ArrayList<>();
    
    //----------------------------funções de procura em todos os sentidos------------------------------
    private boolean rightSearch (String word,int line, int column){
        if (column + word.length() <= matrix[line].length) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line][i+column].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    private boolean leftSearch (String word,int line, int column){
        if (column+1 - word.length() >= 0) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line][column-i].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    private boolean downSearch (String word,int line, int column){
        if (line + word.length() <= matrix[line].length) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[i+line][column].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    private boolean upSearch (String word,int line, int column){
        if ((line - word.length()+1) >= 0) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line-i][column].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    private boolean downRightSearch (String word,int line, int column){
        if (column + word.length() <= matrix[line].length && line + word.length() <= matrix[line].length) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line+i][i+column].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    private boolean upRightSearch (String word,int line, int column){
        if (column + word.length() <= matrix[line].length && (line - word.length()+1)  >= 0) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line-i][i+column].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    private boolean downLeftSearch (String word,int line, int column){
        if (column+1 - word.length() >= 0 && line + word.length() <= matrix[line].length) {
            int i;
            for (i = 0; i < word.length(); i++) {
                if (matrix[line+i][column-i].charAt(0) != word.charAt(i)) return false;
            }
            return i == word.length();
        }
        return false;
    }

    private boolean upLeftSearch (String word,int line, int column){
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
    private void findWord (String word) {
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
    private void rightPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line][i + column] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    private void leftPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line][column-i] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    private void downPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line+i][column] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    private void upPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line - i][column] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    private void downRightPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line + i][column + i] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    private void upRightPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line-i][i + column] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    private void downLeftPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line+i][column-i] = String.valueOf(word.charAt(i)).toUpperCase();
    }

    private void upLeftPrint (String word, int line, int column) {
        for (int i = 0; i < word.length(); i++) solutionMatrix[line-i][column-i] = String.valueOf(word.charAt(i)).toUpperCase();
    }
    //-----------------------------------------------------------------------------------

    //funçao de escrever a soluçao na matriz de acordo com a direçao
    private void writeWordInSolution (Word w) {
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
    private boolean checkColisions () {
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

    public boolean solve(char[][] soup, ArrayList<String> wds) {

        words = new ArrayList<>(wds);
        matrix= new String[soup[0].length][soup[0].length];
        for (int i = 0; i < soup[0].length; i++) {
            for (int j = 0; j < soup[0].length; j++) {
                matrix[i][j] = String.valueOf(soup[i][j]);
            }
        }
        foundWords.clear();
        repeatedWords.clear();



        //Iniciar a matrix solução com "."
        solutionMatrix = new String[matrix[0].length][matrix[0].length];
        for (String[] line : solutionMatrix) Arrays.fill(line,".");

        //Procurar as palavras
        for (String word: words) findWord(word);

        //Verificaçao de colisoes
        if(!checkColisions()){
            return false;
        }
        //Palavras sem solução
        for (Word w : foundWords) {
            if(w.getDirection().getOrientation().equals("UN")){
                return false;
            }
        }
        //Escrever na matrix solução
        for (Word w : foundWords) {
            writeWordInSolution(w);
        }
        System.out.println();
    return  true;
    }
}