import java.awt.Color;
import java.util.ArrayList;
/**
 * a drawing board that contain an array-list of shape
 * @author Duong Chau
 * @version HW2
 */
public class DrawingBoard {
  private ArrayList<Shapes> shapeList;
  
  /**
   * constructor
   * instantiate the arraylist of shape
   */ 
  public DrawingBoard(){
    shapeList = new ArrayList<Shapes>();
  }
  /**
   * add a plus to the list
   * @param x x-coordinate of the plus
   * @param y y-coordiante of the plus
   * @param size the size of the plus
   * @param color the color of the plus
   */ 
  public void addPlus(int x, int y, int size, Color color){
    Plus plus = new Plus(x, y, size, color);
    shapeList.add(plus);
  }
  /**
   * add a diamond to the list
   * @param x x-coordinate of the diamond
   * @param y y-coordiante of the diamond
   * @param size the size of the diamond
   * @param color the color of the diamond
   */ 
  public void addDiamond(int x, int y, int size, Color color){
    Diamond diamond = new Diamond(x, y, size, color);
    shapeList.add(diamond);
  }
  /**
   * make a miniMe of a shape if it has twice the size of minSize
   * @param direction the position of the miniMe on the original N-E-S-W
   */
  public void addMiniMe(Direction direction){
    java.util.Iterator<Shapes> it = shapeList.iterator();
    Shapes miniShape = null;
    while(it.hasNext()){
      Shapes aShape =  it.next();
      // only make miniMe of shapes that has twice the size of minSize
      if (aShape.getSize() >= aShape.getminSize()*2)
        miniShape = aShape.miniMe(direction);
      
      shapeList.add(miniShape);
    }
  }
  /**
   * remove all of the shapes in the list
   */
  public void removeAll(){
    for (int i = shapeList.size()-1; i >= 0; i--){
      shapeList.remove(i);
    }
  }
  /**
   * make a copy of the list of shapes
   * @return return the new copy list
   */ 
  public ArrayList<Shapes> copyList (){
    ArrayList<Shapes> copyList = new ArrayList<Shapes>();
    java.util.Iterator<Shapes> it = shapeList.iterator();
    
    while(it.hasNext()){
      copyList.add(it.next());
    }
    return copyList;
  }
}