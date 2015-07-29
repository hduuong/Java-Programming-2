import javax.swing.*;
import java.awt.*;

/**
 * the dragon class
 * defined by an starting point, an ending point
 * an iteration level, and a color
 * 
 * @author Duong Chau
 * @version Hw4
 */ 

public class Dragon {
  //instance variables
  private double x,y;     // the end point
  private double x0,y0;   // the starting point
  private int iteration;  // the iteration level
  private Color color;    // the color of the dragon
  /**
   * constructor
   * instantiate the instance variables
   * @param startx, starty the starting points
   * @param endx, endy the ending points
   * @param num the number of iteration
   */ 
  public Dragon (double startx, double starty,
                double endx, double endy, int num, Color rgb){
    
    x0 = startx;
    y0 = starty;
    iteration = num;
    x = endx;
    y = endy;
    color = rgb;
  }
  /**
   * get the x-coordinate of the starting point
   * @return the double type x-coordinate
   */ 
  public double getX() {
    return x0;
  }
  /**
   * get the y-coordinate of the starting point
   * @return an double type y-coordinate
   */ 
  public double getY() {
    return y0;
  }
  
  /**
   * get the x-coordinate of the ending point
   * @return the x-coordinate
   */ 
  public double getEndX() {
    return x;
  }
  /**
   * get the y-coordinate of the ending point
   * @return an int type y-coordinate
   */ 
  public double getEndY() {
    return y;
  }
  /**
   * get the iteration
   * @return an int type iteration number
   */ 
  public int getIterate() {
    return iteration;
  }
  /**
   * change the iteration
   * @param ii an int type number of level
   */
  public void changeIteration(int ii){
    iteration = ii;
  }
  
  /**
   * a drawing method
   * call this method to allow the dragon to draw itself
   * 
   * @param g graphic object 
   * @param itera level of iteration 
   * @param direction NORTH or SOUTH, determine the peak point
   * @param x1,y1 the starting point
   * @param x2,y2 the ending point
   */ 
  public void drawDragon(Graphics g, int itera
                           , double x1, double y1
                           , double x2, double y2
                           , Direction direction){
    g.setColor( color );
    // base case, draw the lines 
    if (itera <= 0){
      g.drawLine((int)x1,(int)y1,(int)x2,(int)y2);}
    
    else {
      //create new peak point - point to the North of the line
      //makes 2 new lines with the new point
      //passes it in the the recursive call
      if (direction == Direction.NORTH) {
        double peakX = x1 + (x2 - x1) * 0.5 - (y1 - y2) * 0.5;
        double peakY = y1 - (x2 - x1) * 0.5 - (y1 - y2) * 0.5;
        drawDragon(g, itera - 1, x1, y1, peakX, peakY, Direction.NORTH);
        drawDragon(g, itera - 1, peakX, peakY, x2, y2, Direction.SOUTH);
      //create new peak point - point to the South of the line
      //makes 2 new lines with the new point
      //passes it in the the recursive call
      } else{
        double peakX = x2 + (x1 - x2) * 0.5 - (y2 - y1) * 0.5;
        double peakY = y2 - (x1 - x2) * 0.5 - (y2 - y1) * 0.5;
        drawDragon(g, itera - 1, x1, y1, peakX, peakY, Direction.NORTH);
        drawDragon(g, itera - 1, peakX, peakY, x2, y2, Direction.SOUTH);
      }
    }
  }
}