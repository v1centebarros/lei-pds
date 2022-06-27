package lab01;

import java.nio.file.*;
import java.nio.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import static lab01.TryputinGrid.copy_grid;
import static lab01.TryputinGrid.gridtoString;



class WSGenerator{
    
    public static void main(String[] args){
        
    
    }
    
    private List<int[]> directions = Arrays.asList(new int[][] {{1,0},{0,1},{1,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1}});
    private int grid_size = 13;
    private Character[][] grid = new Character[grid_size][grid_size];
    
    
    
    public String[] readFile(String filepath){
        List<String> lines;
        try{
        
            Path p= Paths.get(filepath);
            lines = Files.readAllLines(p);
                    
        }
        catch(Exception e){
            System.out.println("Could not read file");
            return null;
        }
        return lines.toArray(new String[0]);
    }
    
    public boolean validation(String[] lines){
        Pattern regex = Pattern.compile("[$&+:=?@#|'<>.^-]|[A-Z]");
        
        for(String line : lines){
            if(regex.matcher(line).find()){
               return false;
            }
        }
        return true;
    }
    
    
    public String[] getSolutions(String[] lines){
        ArrayList<String> solutions_list = new ArrayList<String>();
        
        for(String line : lines){
            solutions_list.addAll(Arrays.asList(line.split("( |;|,)")));
        }
        String[] solutions = new String[solutions_list.size()];
        solutions = solutions_list.toArray(solutions);
        return solutions;
    }
    
     
     
     public String makeSoup(String[] solutions){
        
        int ntries = 0;

        ArrayBlockingQueue<String> solutions_queue = new ArrayBlockingQueue<String>(solutions.length);
        List<String>shuffled_solutions = Arrays.asList(solutions);
        Character[][] temp_grid = new Character[grid_size][grid_size];
        
        
        System.out.println("Making soup!!!");
         while(ntries < 100){
            
           solutions_queue.clear();
           Collections.shuffle(shuffled_solutions);
           solutions_queue.addAll(shuffled_solutions);
            
            
           while(!solutions_queue.isEmpty()){
               if(!tryPutInGrid(solutions_queue.poll(),temp_grid,grid_size)){
                   copy_grid(temp_grid,grid,grid_size);
                   break;
               }
           }
           
           if(solutions_queue.isEmpty()){
               return gridtoString(temp_grid,grid_size);
               
           }
           
           ntries++;
         }
         
         if(grid == null){
            System.out.println("Could not");
         }
         return null;
     }
     
    public boolean tryPutInGrid(String word,Character[][] temp_grid,int grid_size){ // tries putting a string in  one free coord of the grid
        
        Collections.shuffle(directions);
        ArrayBlockingQueue<int[]> directions_queue = new ArrayBlockingQueue<int[]>(directions.size());
        directions_queue.addAll(directions);
         
        Character[] char_array  = word.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        
        ArrayBlockingQueue<Character> char_queue = new ArrayBlockingQueue<Character>(word.length()); 
        char_queue.addAll(Arrays.asList(char_array));
         
        int x = (int)(Math.random()*(grid_size-1));
        int y = (int)(Math.random()*(grid_size-1));
        
        while(!(grid[x][y]==null)){
            x = (int)(Math.random()*(grid_size-1));
            y = (int)(Math.random()*(grid_size-1));
        }
        
        System.out.printf("x:%d y:%d\n",x,y);
        
        
        copy_grid(temp_grid,grid,grid_size);
         
         while(!directions_queue.isEmpty()){
             
             int [] dir = directions_queue.poll();
             // System.out.printf("(%d,%d)\n",dir[0],dir[1]);
             int i = 0; // indica os ciclos
             while(true){

                 try{
                     if(temp_grid[x+i*dir[1]][y+i*dir[0]]==null){
                         char word_char = char_queue.poll();
                         temp_grid[x+i*dir[1]][y+i*dir[0]] = word_char; 
                     } else {
                         char word_char = char_queue.peek();
                         if(!temp_grid[x+i*dir[1]][y+i*dir[0]].equals(word_char)){
//                             System.out.println("Here 3");
                             System.out.printf("(%d,%d)->(%d,%d)\n",x+i*dir[1],y+i*dir[0],dir[1],dir[0]);
                             System.out.println(gridtoString(temp_grid,grid_size));
                             
                             break;
                         }
                         char_queue.poll();
                     }
//                     System.out.println("Here 2");
                     System.out.printf("(%d,%d)->(%d,%d)\n",x+i*dir[1],y+i*dir[0],dir[1],dir[0]);
                     System.out.println(gridtoString(temp_grid,grid_size));
                     
                 }
                 catch(Exception e){
                    break;
                 }
                 i++;
                 if(char_queue.isEmpty()){
                     System.out.println("Here 1");
                     System.out.println(gridtoString(temp_grid,grid_size));
                     return true;
                 }
             }
             copy_grid(temp_grid,grid,grid_size);
             char_queue.clear();
             char_queue.addAll(Arrays.asList(char_array));
    }
         return false;
    }
     
    public void copy_grid(Character[][] grid_,Character[][] grid_source,int grid_size)
    {
        // Creating object of String class
        for(int x = 0;x<grid_size;x++){
            for(int y=0;y<grid_size;y++){
                grid_[x][y] = grid_source[x][y];
               
            }
           
        }
    }
     
      public static String gridtoString(Character[][] grid,int grid_size)
    {
        // Creating object of String class
        StringBuilder sb = new StringBuilder();
 
        for(int x = 0;x<grid_size;x++){
            for(int y=0;y<grid_size;y++){
                if(grid[x][y]==null){
                    sb.append("-");
                } else {
                    sb.append(grid[x][y]);
                }
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
}