import java.awt.*;
import java.util.*;
/**
 * an abstract super class of plus and diamond
 * contain all of the methods that share among the 2 sub class 
 * and abstract methods that will be overrided.
 * 
 * @author Duong Chau
 * @version HW2
 */
public abstract class Shapes {
  private int x,y;
  private int size;        //size = the total length/width of the shape
  private Color color;
  private int shapeType;
  
  // the minimun size for the shapes to be created
  public static final int minSize = 6;    
  /**
   * Constructor that instantiate the shape
   * @param x1 x-coordinate of the shape 
   * @param y1 y-coordiante of the shape
   * @param dimension the size of the shape
   * @param rgb the color of the shape 
   */ 
  public Shapes (int x1, int y1, int dimension, Color rgb){
    if( dimension < minSize ) 
      throw new IllegalArgumentException
      ("cannot be smaller than the size of " + minSize);
                                            
    x = x1;
    y = y1;
    size = dimension;
    color = rgb;
  }
  
  /**
   * change the location of the shape
   * @param dx a new x coordinate for the shape
   * @param dy a new y coordinate for the shape
   */ 
  public void changeLocation (int dx, int dy){
    x=dx;
    y=dy;
  }
  
  /**
   * change the size of the shape
   * @param aSize a new size for the shape
   */ 
  public void changeSize (int aSize){
    size = aSize;
  }
  
  /**
   * change the color of the shape
   * @param aColor a new Color for the shape
   */ 
  public void changeColor (Color aColor){
    color = aColor;
  }
  /**
   * get the x-coordinate of the shape
   * @return the x-coordinate
   */ 
  public int getX() {
    return x;
  }
  /**
   * get the y-coordinate of the shape
   * @return an int type y-coordinate
   */ 
  public int getY() {
    return y;
  }
  /**
   * get the size of the shape
   * @return an int type size
   */ 
  public int getSize() {
    return size;
  }
  /**
   * get the color of the shape
   * @return a COlor type color
   */
  public Color getColor() {
    return color;
  }
  
  /**
   * get the color of the shape
   * @return a COlor type color
   */
  public int getminSize() {
    return minSize;
  }
  
  /**
   * an abstact copy method the will be overrided in the sub classes
   */ 
  public abstract Shapes copy();
  /**
   * an abstact miniMe method the will be overrided in the sub classes
   */ 
  public abstract Shapes miniMe(Direction direction);
  
  public abstract void paintComponent(Graphics g);
}
