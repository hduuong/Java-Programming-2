import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * perform adding a random specified shape after 
 * clicking a button
 * 
 * @author Duong Chau
 * @version HW3
 */ 
public class AddShapeButtonListener implements ActionListener {
  // instance variables
  private DrawingBoard board;
  private static Random random =
            new Random(System.currentTimeMillis());
  
  /**
   * constructor
   * instantiate the instance variables
   * 
   * @param shapeList the drawing board
   */ 
  public AddShapeButtonListener(DrawingBoard shapeList){
    board = shapeList;
  }
  /**
   * perform adding a random shapes
   * 
   * @param e an Action Event
   */ 
  public void actionPerformed(ActionEvent e) {
    
    JButton b = (JButton) e.getSource();
    
    if(b.getActionCommand().equals("plus")){
      board.addShapes(this.randomPlus());  
     
    }
    else
      board.addShapes(this.randomDiamond());  
  } 
  /**
   * instantiates a random Plus
   * @return a random plus
   */ 
  private Shapes randomPlus(){
    return new Plus(random.nextInt(400),
                      random.nextInt(400),
                      random.nextInt(100),
                      new Color(random.nextInt(256),
                      random.nextInt(256),
                      random.nextInt(256)));
                      
  }
  /**
   * instantiates a random Diamond
   * @return a random diamond
   */ 
  private Shapes randomDiamond(){
    return new Diamond(random.nextInt(400),
                      random.nextInt(400),
                      random.nextInt(100),
                      new Color(random.nextInt(256),
                      random.nextInt(256),
                      random.nextInt(256)));
                      
  }
  
}