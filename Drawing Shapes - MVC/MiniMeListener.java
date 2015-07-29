import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * add MiniMe shapes to all existed shapes in the board
 * update the viewer after added the mini shapes
 * 
 * @author Duong Chau
 * @version HW3
 */ 
public class MiniMeListener implements MouseListener {
  // instance variables
  private DrawingBoard board;
  private VIEWER_2 view2;
  private VIEWER_1 view1;
  
  /**
   * constructor 
   * instantiate the instance variables
   * @param shapeList the drawing board
   * @param v1 the text display
   * @param v2 the graphical display
   */ 
  public MiniMeListener (DrawingBoard shapeList, VIEWER_1 v1,
                        VIEWER_2 v2){
    board = shapeList;
    view1 = v1;
    view2 = v2;
  }
  /**
   * add a mini shapes after click on panel
   * update the 2 viewers
   * @param e a MouseEvent
   */ 
  public void mouseClicked(MouseEvent e) {
    board.addMiniMe(Direction.NORTH);
    view1.listChanged();
    view2.listChanged();
  }
  // all of the other mouse event methods
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }
  public void mousePressed(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
}