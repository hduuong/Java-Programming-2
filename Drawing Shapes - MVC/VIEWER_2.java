import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

/**
 * Graphical viewer that displays the shapes that added to the
 * drawingboard
 * 
 * @author Duong Chau
 * @version HW3
 */ 
public class VIEWER_2 extends JPanel implements VIEWer {
  // instance variables
  private DrawingBoard board;
  
  /**
   * constructor that instantiate the instance variable
   * and a panel
   * 
   * @param shapeList a DrawingBoard object
   */ 
  public VIEWER_2 ( DrawingBoard shapeList ){
    super( );
    setPreferredSize( new Dimension(400,400) );
    setBackground(Color.white);
    
    board = shapeList;
    board.registerViewer( this );
  }
  
  /**
   * the implement method, repaint the panel if there are changes
   */ 
  public void listChanged (){
    repaint();
  }
  
  public void paintComponent( Graphics g ) {
    // repaint background
    super.paintComponent( g );
    
    ArrayList<Shapes> shape = board.copyList();
    java.util.Iterator<Shapes> it = shape.iterator();
    while (it.hasNext()){
      Shapes aShape =  it.next();
      aShape.paintComponent(g);
    }
  }
    
}
