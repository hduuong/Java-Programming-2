/**
 * basic interface for viewers of a DrawingBoard
 * viewers can be added to a drawingboard, which will notify the
 * viewers to update the displays
 * 
 * @author Duong Chau
 * @version HW3
 */ 
public interface View {
  public void iterChanged ();
  
}