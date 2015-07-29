import javax.swing.*;
import java.awt.*;

/**
 * a super class of all the dragon tails
 * 
 * @author Duong Chau
 * @version Hw4
 */ 

public abstract class Tails {
  private int x,y;    //represent end point
  private double x0,y0;  //represent start point
  private int iteration; //represent the interation
  
  public Tails (double startx, double starty, int num){
    if (num < 0 ) throw new IllegalArgumentException();
    x0 = startx;
    y0 = starty;
    iteration = num;
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
   * get the iteration
   * @return an int type iteration number
   */ 
  public int getIterate() {
    return iteration;
  }
  /**
   * change the iteration
   * @param 
   */
  public void changeIteration(int ii){
    iteration = ii;
  }
  public abstract double getEndY();
  public abstract double getEndX();
  /**
   * abstract paintComponent method
   * this make the sub classes have to override the method 
   */ 
  public abstract void drawDragon(Graphics g, int itera
                                    , double x1, double y1
                                    , double x2, double y2
                                    , Direction direction);
}