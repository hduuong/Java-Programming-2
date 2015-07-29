import javax.swing.*;
import java.awt.*;

/**
 * one of the tails
 * 
 * @author Duong Chau
 * @version Hw4
 */ 

public class TailsN extends Tails{
  private double x,y;            //the end point
  /**
   * constructor
   * instantiate the end points
   * @param startx, starty the starting points
   * @param endx, endy the ending points
   * @param num the number of iteration
   */ 
  public TailsN (double startx, double starty,
                double endx, double endy, int num){
    super(startx, starty, num);
    x = endx;
    y = endy;
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