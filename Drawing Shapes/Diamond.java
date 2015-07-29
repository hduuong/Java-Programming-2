import java.awt.Color;
/**
 * a class that extends the shape super class
 * override the copy and miniMe method that specify to a diamond
 * 
 * @author Duong Chau
 * @version HW2
 */ 
public class Diamond extends Shapes {
  
  /**
   * constructor
   * @param x x-coordinate of the shape 
   * @param y y-coordiante of the shape
   * @param size the size of the shape
   * @param color the color of the shape 
   */ 
  public Diamond (int x, int y, int size, Color color){
    super (x, y, size, color);
  }
  
  /**
   * make a new diamond with the same state as the orgininal
   * @return a diamond object
   */ 
  public Diamond copy (){
    Diamond duplicate;
    int x = this.getX();
    int y = this.getY();
    int size = this.getSize();
    Color color = this.getColor();
    
    duplicate = new Diamond (x,y,size,color);
    return duplicate;
  }
  /**
   * make a new diamond with half size of the original and its complementary color
   * and add it to the direction of the original provided by user
   * @param direction the NORTH SOUTH EAST WEST of the original
   */ 
  public Diamond miniMe (Direction direction){
    // get the original values
    int newX = this.getX();
    int newY = this.getY();
    int newSize = this.getSize();
    Color color = this.getColor();
    
    // calculate the new color for the shape
    int red = 255 - color.getRed();
    int blue = 255 - color.getBlue();
    int green = 255 - color.getGreen();
    Color newColor = new Color(red,green,blue);
    
    // validate the size of the shape
    if (newSize < this.getminSize()) 
      throw new IllegalArgumentException("cannot make a new shape with size < 6");
    
    // add the mini-diamond in correct position base on the passed in direction
    Diamond miniDiamond = null;
    if (direction == Direction.NORTH)
      miniDiamond = new Diamond (newX, newY - newSize, newSize/2, newColor);
    else if (direction == Direction.SOUTH)
      miniDiamond = new Diamond (newX, newY + newSize, newSize/2, newColor);
    else if (direction == Direction.EAST)
      miniDiamond = new Diamond (newX + newSize, newY, newSize/2, newColor);
    else 
      miniDiamond = new Diamond (newX - newSize, newY, newSize/2, newColor);
    
    return miniDiamond;
  }
}