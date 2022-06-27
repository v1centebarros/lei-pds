package lab01;

import java.util.*;
import java.io.*;

public class WSSolver {
    public static void main (String[] args) throws IOException {
		int linhas = 0;
        int colunas = 0;
        int aux_colunas = 0;
        String palavras = "";
        String[] solucao = {};
        List<String> aux = new ArrayList<>();
        List<String> x = new ArrayList<>();
        ArrayList<List<String>> grid = new ArrayList<>();
        Map<String, Coord> solutions = new HashMap<String, Coord>();
        
        //Scanner scf = new Scanner(new File(args[1]));
        File fich = new File("C:/Users/35191/Desktop/PDS/practical-g206/lab01/sdl_01.txt");
        Scanner scf = new Scanner(fich);
        while(scf.hasNextLine()) {
            String data = scf.nextLine();

            //verificar se há linhas vazias a meio do programa
            if(data.trim().isEmpty()){
                System.out.println("formato inválido, Linha vazia");
                //System.exit(0);
            }
            
            //saber que já não faz parte da sopa quando chegamos às palavras em minusculo.
            if(checkLowerCase(data)){
                // string palavras vai ter as palavras que temos de procurar
                palavras  = palavras + " " +  data;

            }else{
                //adicionar a uma grid a sopa de letras
                x = Arrays.asList(data.split(""));
                grid.add(x);
            }
        }

        linhas = grid.size();
        
        for(int i=0; i<grid.size();i++){
            aux_colunas = grid.get(i).size();
            if(aux_colunas > colunas)
                colunas = aux_colunas;
        }  

        solucao = wordarray(palavras);
        
        //validação quadrado e maiusculas
        if(linhas>40 || colunas>40 || linhas != colunas){
            System.out.println("ERROR - formato inválido");
            System.exit(0);
        }


        for(int i=1; i<solucao.length;i++){
            System.out.println(findWord(solucao[i],grid).toString());
        }
        


        //print linhas e colunas
        System.out.println("Linhas: " + linhas);
        System.out.println("Colunas: " + colunas);

        //print sopa
        for(int i = 0; i<grid.size();i++)
            System.out.println(grid.get(i).toString());

        //print soluções
        for(int i=0;i<solucao.length;i++){
            System.out.println(solucao[i]);
        }

        
        scf.close();

	}

    // ficar com array de palavras
    private static String[] wordarray(String s) {
        String string = s.replaceAll(";", " ");
        string = string.replaceAll(",", " ").toUpperCase();
        
        String[] palavras = string.split(" ");
        return palavras;
    }
    
    // verifica se há uma lower case numa string
    static public boolean checkLowerCase(String s){
        boolean lowercase = false;
        for(int i = 0; i<s.length();i++){
            if(Character.isLowerCase(s.charAt(i))){
                lowercase = true;
            }
        }
        return lowercase;
    }

    static public boolean checkForNumbers(String s){
        boolean lowercase = false;
        for(int i = 0; i<s.length();i++){
            if(Character.isLowerCase(s.charAt(i))){
                lowercase = true;
            }
        }
        return lowercase;
    }

    public static Map<String, Coord> findWord(String word, ArrayList<List<String>> grid)
    {
        String firstLetter = String.valueOf(word.charAt(0));
        List<String> aux = new ArrayList<>(); 
        Map<String, Coord> solutions = new HashMap<String, Coord>();
        Coord a = new Coord(0, 0, 0, 0);

        for (int x = 0; x < grid.size(); x++)
        {
            for (int y = 0; y < grid.get(0).size(); y++)
            {   
                aux = grid.get(x);
                if (aux.get(y).equals(firstLetter))
                {
                    if (checkLeft(x, y, word, grid)){      
                        a = new Coord(x, y, x, (y - word.length() + 1));
                        solutions.put(word,a);
                    }
                    else if (checkRight(x, y, word, grid)){
                        a = new Coord(x, y, x, (y + word.length() - 1));
                        solutions.put(word, a);
                    }
                    else if (checkDown(x, y, word, grid)){
                        a = new Coord(x, y, (x + word.length() - 1), y);
                        solutions.put(word, a);
                    }
                    else if (checkUp(x, y, word, grid)){
                        a = new Coord(x, y, (x - word.length() + 1), y);
                        solutions.put(word, a);
                    }
                    else if (checkLeftUp(x, y, word, grid)){
                        a = new Coord(x, y, (x - word.length() + 1), (y - word.length() + 1));
                        solutions.put(word, a);
                    }
                    else if (checkRightUp(x, y, word, grid)){
                        a = new Coord(x, y, (x - word.length() + 1), (y + word.length() - 1));
                        solutions.put(word, a);
                    }
                    else if (checkLeftDown(x, y, word, grid)){
                        a = new Coord(x, y, (x + word.length() - 1), (y - word.length() + 1));
                        solutions.put(word, a);
                    }
                    else if (checkRightDown(x, y, word, grid)){
                        a = new Coord(x, y, (x + word.length() - 1), (y + word.length() - 1));
                        solutions.put(word, a);
                    }
                }
            }
        }
        return solutions;
    }

    public static boolean checkLeft(int x, int y, String word, ArrayList<List<String>> grid)
    {
        List<String> aux = new ArrayList<>();
        if ((y+1) - word.length() < 0){
            return false;
        }

        int index = x;
        for (char letter : word.toCharArray())
        {
            aux = grid.get(index);
            if (!aux.get(y).equals(Character.toString(letter))){
                return false;
            }
            y--;
        }

        return true;
    }

    public static boolean checkRight(int x, int y, String word, ArrayList<List<String>> grid)
    {
        List<String> aux = new ArrayList<>();
        if (y + word.length() > grid.get(0).size()){
            return false;
        }

        int index = x;
        for (char letter : word.toCharArray())
        {
            aux = grid.get(index);
            if (!aux.get(y).equals(Character.toString(letter))){
                return false;
            }
            y++;
            
        }

        return true;
    }



    public static boolean checkDown(int x, int y, String word, ArrayList<List<String>> grid)
    {
        List<String> aux = new ArrayList<>();
        if (x + word.length() > grid.size()){
            return false;
        }
            
        int index = x;
        for (char letter : word.toCharArray())
        {
            aux = grid.get(index);
            if (!aux.get(y).equals(Character.toString(letter))){
                return false;
            }
            index++;
        }

        return true;
    }


    public static boolean checkUp(int x, int y, String word, ArrayList<List<String>> grid)
    {
        List<String> aux = new ArrayList<>();
        if ((x+1) - word.length() < 0){
            return false;
        }

        int index = x;
        for (char letter : word.toCharArray())
        {
            aux = grid.get(index);
            if (!aux.get(y).equals(Character.toString(letter))){
                return false;
            }
            index--;
        }

        return true;
    }

     
    public static boolean checkLeftUp(int x, int y, String word, ArrayList<List<String>> grid)
    {
        List<String> aux = new ArrayList<>();
        if ((y+1) - word.length() < 0 || (x+1) - word.length() < 0){
            return false;
        }
        
        int indexX = x;
        int indexY = y;
        for (char letter : word.toCharArray())
        {
            aux = grid.get(indexX);
            if (!aux.get(indexY).equals(Character.toString(letter))){
                return false;
            }    
            indexX--;
            indexY--;
        }

        return true;
    }

    public static boolean checkRightUp(int x, int y, String word, ArrayList<List<String>> grid)
    {
        List<String> aux = new ArrayList<>();
        if (y + word.length() > grid.get(0).size() || (x+1) - word.length() < 0){
            return false;
        }

        int indexX = x;
        int indexY = y;
        for (char letter : word.toCharArray())
        {
            aux = grid.get(indexX);
            if (!aux.get(indexY).equals(Character.toString(letter))){
                return false;
            } 
            indexX--;
            indexY++;
        }

        return true;
    }

    public static boolean checkLeftDown(int x, int y, String word, ArrayList<List<String>> grid)
    {
        List<String> aux = new ArrayList<>();

        if ((y+1) - word.length() < 0 || x + word.length() > grid.size()){
            return false;
        }

        int indexX = x;
        int indexY = y;
        for (char letter : word.toCharArray())
        {
            aux = grid.get(indexX);
            if (!aux.get(indexY).equals(Character.toString(letter))){
                return false;
            }
            indexX++;
            indexY--;
        }

        return true;
    }
     
    public static boolean checkRightDown(int x, int y, String word, ArrayList<List<String>> grid)
    {
        List<String> aux = new ArrayList<>();

        if (y + word.length() > grid.get(0).size() || x + word.length() > grid.size()){
            return false;
        }

        int indexX = x;
        int indexY = y;
        for (char letter : word.toCharArray())
        {
            aux = grid.get(indexX);
            if (!aux.get(indexY).equals(Character.toString(letter))){
                return false;
            }
            indexX++;
            indexY++;
        }

        return true;
    } 

    
    
}
