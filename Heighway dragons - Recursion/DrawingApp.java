
import javax.swing.*;
import java.awt.*;

/**
 * a running class that creates a Frame and puts all panels
 * into that frame
 * 
 * hold a model -a list of dragons- that contains 4 dragons
 * with different end points
 * 
 * @author Duong CHau
 * @version Hw4
 */ 
public class DrawingApp {
  
  //instance variable - the model
  private Model model;
  
  
  /**
   * constructor that instantiate the instance variable
   * creates 4 dragon objects and adds to the the model
   * makes frame and viewers
   */ 
  public DrawingApp(){
    //creates 4 dragon with different end points
    Dragon t1 = new Dragon(250,250,250,62,0,Color.RED);
    Dragon t2 = new Dragon(250,250,250,438,0,Color.BLUE);
    Dragon t3 = new Dragon(250,250,62,250,0,Color.GREEN);
    Dragon t4 = new Dragon(250,250,438,250,0,Color.BLACK);
    
    // instantiate the model and add the dragons
    model = new Model();
    model.addDragon(t1);
    model.addDragon(t2);
    model.addDragon(t3);
    model.addDragon(t4);
    
    //creates a frame
    JFrame win = new JFrame( "Viewer" );
    win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    // instantiate graphical viewer
    GraphicDisplay graphic = new GraphicDisplay(model);
    
    // instantiate the slider and the slider viewer
    JSlider slider1 = new JSlider(0,30,0);
    JSliderListener slider = new JSliderListener(model,slider1);
    slider1.addChangeListener(slider);
    
    // add the viewers into the frame;
    win.getContentPane().add(slider1, BorderLayout.SOUTH);
    win.getContentPane().add(graphic, BorderLayout.CENTER);
    
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