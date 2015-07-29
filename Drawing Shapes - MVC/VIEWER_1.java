import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
/**
 * text viewer that displays the numbet of shapes 
 * that added to thedrawingboard
 * 
 * @author Duong Chau
 * @version HW3
 */ 
public class VIEWER_1 extends JPanel implements VIEWer {
  
  // instant variables
  private JLabel shapeDisp;
  private DrawingBoard board;
  
  /**
   * constructor that instantiate the instance variable
   * and a panel
   * 
   * @param shapeList a DrawingBoard object 
   */ 
  public VIEWER_1 ( DrawingBoard shapeList ){
    super( );
    setPreferredSize( new Dimension(200,30) );
    setBackground(Color.yellow);
    
    shapeDisp = new JLabel( "number of shapes" );
    add( shapeDisp );
    
    board = shapeList;
    board.registerViewer( this );    
  }
  
  /**
   * a method that update the text display
   * whenever the board is changed
   */ 
  public void listChanged (){
    int p = 0;
    int d = 0;
    ArrayList<Shapes> shape = board.copyList();
    java.util.Iterator<Shapes> it = shape.iterator();
    while(it.hasNext()){
      Shapes aShape =  it.next();
      if (aShape instanceof Diamond) d = d + 1;
      else if (aShape instanceof Plus) p = p + 1;
    }
    shapeDisp.setText("number of pluses: " + p +
                      " number of diamonds: " + d);
  }
}