import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * a slider that control the iteration of the dragons
 * 
 * @author Duong Chau
 * @version Hw4
 */ 
public class JSliderListener implements ChangeListener {
  //instance variables
  private Model model;
  private JSlider slider;
  
  /**
   * constructor
   * instantiate the instance variables
   * @param dragon the model
   * @param control the slider
   */ 
  public JSliderListener(Model dragon, JSlider control){
    model = dragon;
    slider = control;
  }
  
  /**
   * call the changeAllIteration method from the drawing board 
   * to change the iteration level of all dragons
   * @param e a change event
   */
  public void stateChanged(ChangeEvent e){
    model.changeAllIteration(slider.getValue());
  }
}