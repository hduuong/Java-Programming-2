import javax.swing.*;
import java.awt.*;


/**
 * a class the specify the characteristic of a dragon
 * 
 * @author Duong Chau
 * @version Hw4
 */ 

public class Dragon {
  private double x0,y0; // the starting points
  private double x,y;   // the end point
  private int iteration;      // the iteration level
  private Color color;  // the color of the dragon
  /**
   * constructor
   * instantiate the end points
   * @param startx, starty the starting points
   * @param endx, endy the ending points
   * @param num the number of iteration
   */ 
  public Dragon (double startx, double starty, int level
                   ,double endx, double endy, Color rgb){
    x0 = startx;
    y0 = starty;
    iteration = level;
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
   * 
   * @param g
   * @param itera
   * @param direction
   * @param x1,y1
   * @param x2,y2 
   */ 
  public void drawDragon(Graphics g, int itera
                           , double x1, double y1
                           , double x2, double y2
                           , Direction direction){
    g.setColor( Color.RED );
    if (itera <= 0){
      g.drawLine((int)x1,(int)y1,(int)x2,(int)y2);}
    
    else {
      if (direction == Direction.NORTH) {
        double peakX = x1 + (x2 - x1) * 0.5 - (y1 - y2) * 0.5;
        double peakY = y1 - (x2 - x1) * 0.5 - (y1 - y2) * 0.5;
        drawDragon(g, itera - 1, x1, y1, peakX, peakY, Direction.NORTH);
        drawDragon(g, itera - 1, peakX, peakY, x2, y2, Direction.SOUTH);
      } else{
        double peakX = x2 + (x1 - x2) * 0.5 - (y2 - y1) * 0.5;
        double peakY = y2 - (x1 - x2) * 0.5 - (y2 - y1) * 0.5;
        drawDragon(g, itera - 1, x1, y1, peakX, peakY, Direction.NORTH);
        drawDragon(g, itera - 1, peakX, peakY, x2, y2, Direction.SOUTH);
      }
    }
  }
}