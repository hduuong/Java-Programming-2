import java.awt.Color;
/**
 * a class that extends the shape super class
 * override the copy and miniMe method that specify to a plus
 * 
 * @author Duong Chau
 * @version HW2
 */
public class Plus extends Shapes {
  /**
   * constructor
   * @param x x-coordinate of the shape 
   * @param y y-coordiante of the shape
   * @param size the size of the shape
   * @param color the color of the shape 
   */ 
  public Plus (int x, int y, int size, Color color){
    super (x, y, size, color);
  }
  
  /**
   * make a new plus with the same state as the orgininal
   * @return a plus object
   */ 
  public Plus copy (){
    Plus duplicate;
    int x = this.getX();
    int y = this.getY();
    int size = this.getSize();
    Color color = this.getColor();
    
    duplicate = new Plus (x,y,size,color);
    return duplicate;
  }
  /**
   * make a new plus with half size of the original
   * and add it to the direction of the original provided by user
   * @param direction the NORTH SOUTH EAST WEST of the original
   * @return a plus object
   */  
  public Plus miniMe (Direction direction){
    // get the original values
    int newX = this.getX();
    int newY = this.getY();
    int newSize = this.getSize()/2;
    Color color = this.getColor();
    
    
    // validate the size of the shape
    if (newSize < this.getminSize())
      throw new IllegalArgumentException("cannot make a new shape with size < 6");
    
    // add the mini-plus in correct position base on the passed in direction
    Plus miniPlus = null;
    if (direction == Direction.NORTH)
      miniPlus = new Plus (newX, newY - newSize*2, newSize, color);
    else if (direction == Direction.SOUTH)
      miniPlus = new Plus (newX, newY + newSize*2, newSize, color);
    else if (direction == Direction.EAST)
      miniPlus = new Plus (newX + newSize*2, newY, newSize, color);
    else
      miniPlus = new Plus (newX - newSize*2, newY, newSize, color);
   
    return miniPlus;
  }
}