import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.awt.*;

/**
 * 
 * 
 * @author DUong Chau
 * @version HW5
 */ 
public class Model {
  //instance variables
  int m; //number of node
  int n; //number of edges
  int[][] map; //the n*n matrix that contains HAS/NO_EDGE
  ArrayList<String> names; //the list that contains the destinations
  ArrayList<Integer> paths; // the list that contains the edges
  
  private static final int NO_EDGE = 0;
  private static final int HAS_EDGE = 1;
  private static final int EXPLORING = 2;
  private static final int DEAD_END = 3;
  
  public Model (File file) throws FileNotFoundException{
    names = new ArrayList<String>();
    paths = new ArrayList<Integer>();
     Scanner scan = new Scanner(file);
     m = scan.nextInt();
     n = scan.nextInt();
     map = new int[m][m];
     
     for(int i = 0; i < m; i++){
       names.add(scan.next());
     }
     
     for(int i = 0; i < n*2; i++){
       paths.add(scan.nextInt());
     }
     
     int c = 0;
     while (c < n*2 -1){
       int i = paths.get(c++);
       int j = paths.get(c++);
       map[i][j] = HAS_EDGE;
       map[j][i] = HAS_EDGE;
     }
  
  }
  /**
   * the method that make a copy of the original map
   * this map is going to be changed during the recursion
   * @return a new double int array
   */ 
  public int[][] copyMap() {
    int[][] copy = new int[m][m];
    for(int i = 0; i < m; i++){
      for(int j = 0; j < m; j++){
        copy[i][j] = map[i][j];
      }
    }
    return copy;
  }
  /**
   * user's guild
   * print the destinations
   */
  public void printDestinatons(){
    Iterator<String> it = names.iterator();
    while(it.hasNext()){
      System.out.println(it.next());
    }
  }
  /**
   * check if there is a possible path
   * @param x0 starting point
   * @param x ending point
   * @return true/false
   */ 
  public boolean possiblePaths(int x0, int x){
    boolean f = false;
    
    if(x0>x){
      for(int i = x; i <= x0; i++){
        if(map[i][x] == HAS_EDGE)
          f = true;
      }
    }
    else if(x>x0){
      for(int i = x0; i <= x; i++){
        if(map[i][x0] == HAS_EDGE)
          f = true;
      }
    }
    return f;
  }
  
  /**
   * the recursive method that display the path from start to end
   * 
   * @param matrix the map that marked edges 
   * @param start the starting point
   * @param end the ending point
   * @param route the paths taken
   * @return a arraylist of String names of the path
   */ 
  public ArrayList<String> IslandHopping (int[][] matrix, String start, String end, ArrayList<String> route){
    int x0 = 0, x = 0;  //identify starting and ending points
    int[][] tempMatrix = matrix; //a temparary matrix
    ArrayList<String> list = route; //keep track of the path
    String path1 = "", path2 = "";
    
    for (int i = 0; i < names.size(); i++){
      if( names.get(i).compareToIgnoreCase(start) == 0 )
        x0 = i;
      if( names.get(i).compareToIgnoreCase(end) == 0 )
        x = i;
    }
    
    if(!possiblePaths(x0,x)){
      for(int i = 0; i < list.size(); i++){
        list.remove(i);
      }
      list.add("there is no path");
      return list;
    } else {
      if(matrix[x0][x] == HAS_EDGE){
        path1 = names.get(x0);
        path2 = names.get(x);
        list.add(path1);
        list.add(path2);
        return list;
      }
      else {
        
        boolean foundEdge = false;
        for(int i = 0; i < names.size() && !foundEdge; i++){
          if(tempMatrix[x0][i] == HAS_EDGE || tempMatrix[i][x0] == HAS_EDGE){
            tempMatrix[x0][i] = EXPLORING;
            tempMatrix[i][x0] = EXPLORING;
            path1 = names.get(i);
            path2 = names.get(x);
            list.add(names.get(x0));
            foundEdge = true;
          }
        }
        return IslandHopping(tempMatrix, path1, path2, list);
      }
    }
  }
}
