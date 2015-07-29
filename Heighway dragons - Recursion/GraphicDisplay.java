import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * Graphical viewer that displays the dragons
 * 
 * @author Duong Chau
 * @version Hw4
 */ 
public class GraphicDisplay extends JPanel implements View{
  //instance variable
  private Model model;
  
  /**
   * constructor that instantiate the instance variables
   * and creates a panel
   * 
   * @param dragon a model reference
   */ 
  public GraphicDisplay (Model dragon){
    super ();
    setPreferredSize( new Dimension(500,500) );
    setBackground(Color.white);
    
    model = dragon;
    model.register(this);
  }
  
  
  
  //repaint the panel after any changes of iteration occured 
  public void iterChanged(){
    repaint();
  }
  /**
   * a painting method, not actually draw the dragon but call
   * the drawDragon method from each dragon to draw itself
   * 
   * @param g a Graphics object
   */ 
  public void paintComponent( Graphics g ) {
    // repaint background
    super.paintComponent( g );
    
    //make copy of the dragon list
    ArrayList<Dragon> d = model.copyList();
    //iterate through the list
    java.util.Iterator<Dragon> it = d.iterator();
    while (it.hasNext()){
      Dragon t =  it.next();
      //each dragon in the list call the draw method
      t.drawDragon(g,t.getIterate(),t.getX(),t.getY(),
                   t.getEndX(),t.getEndY(),Direction.NORTH);
    }
  }
}