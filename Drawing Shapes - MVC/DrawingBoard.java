import java.awt.Color;
import java.util.ArrayList;
/**
 * a drawing board that contain an array-list of shape
 * @author Duong Chau
 * @version HW2
 */
public class DrawingBoard {
  private ArrayList<Shapes> shapeList;
  private ArrayList<VIEWer> viewList;
  
  private int centerX = 50;  // center of the horizontal slider
  private int centerY = 50;  // center of the vertical slider
  
  /**
   * constructor
   * instantiate the arraylist of shape
   */ 
  public DrawingBoard(){
    shapeList = new ArrayList<Shapes>();
    viewList = new ArrayList<VIEWer>();
  }
  /////////////////////////////////////////
  //// get the center of the sliders /////
  public int getCenterX (){
    return centerX;
  }
  public int getCenterY (){
    return centerY;
  }
  ////////////////////////////////////////
  ///////////////////////////////////////
  
  /**
   * add a plus to the list
   * @param shape a shape that is being added to the list
   */ 
  public void addShapes(Shapes shape){
    shapeList.add(shape);
    
    notifyViewrs ();
  }

  /**
   * make a miniMe of a shape if it has twice the size of minSize
   * @param direction the position of the miniMe on the original N-E-S-W
   */
  public void addMiniMe(Direction direction){
    
    java.util.ArrayList<Shapes> tempList = new java.util.ArrayList<Shapes>();
    
    java.util.Iterator<Shapes> it = shapeList.iterator();
    Shapes miniShape = null;
    while(it.hasNext()){
      Shapes aShape =  it.next();
// only make miniMe of shapes that has twice the size of minSize
//      if (aShape.getSize() >= aShape.getminSize()*2)
        miniShape = aShape.miniMe(direction);
      
      tempList.add(miniShape);
    }
    for(int i = 0; i <= tempList.size()-1 ; i++){
      shapeList.add(tempList.get(i));
    }
  }
  /**
   * remove all of the shapes in the list
   */
  public void removeAll(){
    for (int i = shapeList.size()-1; i >= 0; i--){
      shapeList.remove(i);
    }
    notifyViewrs ();
  }
  /**
   * move all of the shapes in the list
   * @param x x-coordinate
   * @param y y-coordinate
   */
  public void moveAll(int x, int y){
    int xmove = x - centerX;
    int ymove = y - centerY;
    
    for (int i = 0; i < shapeList.size() ; i++){
      Shapes shape = shapeList.get(i);
      shape.changeLocation(xmove + shape.getX(), ymove + shape.getY());
    }
    centerX = x;
    centerY = y;
    
    notifyViewrs ();
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
  /**
   * register the viewers to the DrawingBoard
   * @param view the viewrs
   */ 
  public void registerViewer (VIEWer view){
    viewList.add(view);
    view.listChanged();
  }
  /**
   * a viewers' notifyer
   * it notifies all viewers to modify their states if any changes occur
   */ 
  private void notifyViewrs (){
    for(int i = 0; i < viewList.size(); i++){
      viewList.get(i).listChanged();
    }
  }
}