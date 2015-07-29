import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.awt.*;
/**
 * the running class
 * promt the user for inputs
 * 
 * @author Duong Chau
 * @version HW5
 */ 
public class InputReading {
  
  private Scanner scan;
  
  public InputReading() {
    scan = new Scanner(System.in);
  }
  public String getInput() {
    System.out.print("Enter a destination (from - to): ");
    return scan.next();
  }
  
  public static void main(String[] args) {
    
    InputReading dest = new InputReading();
    Scanner fileScan = new Scanner(System.in);
    
    
    String fileName = fileScan.next();
    boolean done = false;
    while (!done){
      try{
        File file = new File(fileName);
        done = true;
        Model matrix = new Model(file);
        ArrayList<String> checkNames = matrix.names;
        
        System.out.println("      type HELP for destinations");
        System.out.println("           type EXIT to exit the program");
        String start = dest.getInput();
        String end = dest.getInput();
        for(; ;){
          
          //an emptylist that passed into the method as a dummy parameter
          if(start.equals("HELP") || end.equals("HELP")){
            matrix.printDestinatons();
          }
          else if (start.equals("EXIT") || end.equals("EXIT")){
            break;
          }
          else {
            ArrayList<String> empty = new ArrayList<String>();
            ArrayList<String> paths = matrix.IslandHopping(matrix.copyMap(), start, end, empty);
            
            //print paths
            Iterator<String> it = paths.iterator();
            while(it.hasNext()){
              System.out.print(it.next() + " ");
            }
            System.out.println();
          }
          start = dest.getInput();
          end = dest.getInput();
        }
      }catch(FileNotFoundException e){
        done = false;
        System.err.println("invalid file name. try again");
        fileName = fileScan.next();
      }
    }
    
    
    
    
    
    
    
    
  }
}
