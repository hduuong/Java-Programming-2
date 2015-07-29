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
  private Model model;
  private JSlider slider;
  
  /**
   * constructor
   */ 
  public JSliderListener(Model dragon, JSlider control){
    model = dragon;
    slider = control;
  }
  
  /**
   * 
   * @param e a change event
   */
  public void stateChanged(ChangeEvent e){
    
      model.changeAllIteration(slider.getValue());
  }
}