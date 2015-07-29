import javax.swing.*;
import java.awt.*;

/**
 * a running class that creates a Frame and puts all the panels
 * into the frame
 * 
 * @author Duong Chau
 * @version HW3
 */ 
public class DrawingApp{
  // instance variable
  private DrawingBoard board;
  
  /**
   * constructor that instantiate the instance variable
   * creates a represent plus and diamond
   * 
   */ 
  public DrawingApp(){
    // creates a plus and a diamond
    Plus plus = new Plus (200,100,100,Color.red);
    Diamond diamond = new Diamond (100,250,100,Color.blue);
    
    // instantiate the board and add the 2 shapes
    board = new DrawingBoard();
    board.addShapes(plus);
    board.addShapes(diamond);
    
    // creates a frame
    JFrame win = new JFrame( "Viewer" );
    win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    // instantiate 2 viewers, graphical and text display
    VIEWER_1 dispNumber = new VIEWER_1(board);
    VIEWER_2 dispShapes = new VIEWER_2(board);
    
    // add the 2 viewers to the frame
    Container cp = win.getContentPane( );
    cp.add( dispNumber,BorderLayout.PAGE_END );
    cp.add( dispShapes,BorderLayout.CENTER );
    
    // instantiate a mouse event listener
    MiniMeListener click = new MiniMeListener(board,
                                              dispNumber,
                                              dispShapes);
    // asign the mouse event to the graphical display
    dispShapes.addMouseListener(click);
    
    // creates 2 Jbutton for add pluses and diamonds
    JButton addPlus = new JButton("add a plus");
    JButton addDiamond = new JButton("add a diamond");
    
    // set action command for each button
    addPlus.setActionCommand("plus");
    addDiamond.setActionCommand("diamond");
    
// put the buttons in a default panel and add to the frame
    JPanel pane = new JPanel();
    pane.add(addPlus);
    pane.add(addDiamond);
    win.getContentPane().add(pane, BorderLayout.EAST);
    
// instantiates a button listener and asign it to the buttons
    AddShapeButtonListener control = new AddShapeButtonListener(board);
    addPlus.addActionListener(control);
    addDiamond.addActionListener(control);
    
// create 2 sliders
    JSlider slider1 = new JSlider();
    JSlider slider2 = new JSlider();
    
// make the slider2 slides vertically
    slider2.setOrientation(JSlider.VERTICAL);
    slider2.setInverted(true);
    
// instantiate the Slider Listener and asign it to the 2 sliders
    JSliderListener drag = new JSliderListener(board,slider1,slider2);
    slider1.addChangeListener(drag);
    slider2.addChangeListener(drag);
    
// add the sliders to the frame
    win.getContentPane().add(slider1, BorderLayout.NORTH);
    win.getContentPane().add(slider2, BorderLayout.WEST);
    
// set the states of the frame
    win.pack();
    win.setVisible( true );
    win.toFront();
  }
  
  /** Run simulation */
  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        DrawingApp ba = new DrawingApp();
      }
    });
  }
}