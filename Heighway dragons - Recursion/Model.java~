import java.util.ArrayList;
/**
 * model represents 4 Heighway dragons, each with a common tail.
 * The dragons at iteration 0 stretch out in the directions 
 * north, south, east, and west from the tail
 * 
 * @author Duong Chau
 * @version Hw4
 */ 
public class Model {
  
  private ArrayList<Dragon> headsDragon;
  private ArrayList<View> viewList;
  /**
   * constructor
   * instantiate the arraylist of tails object
   */ 
  
  public Model(){
    headsDragon = new ArrayList<Dragon>();
    viewList = new ArrayList<View>();
  }
  
  /**
   * add a dragon to the list of dragons
   * @param dragon a dragon that is being added to the list
   */ 
  public void addDragon(Dragon dragon){
    headsDragon.add(dragon);
    
    notifyViewrs();
  }
  /**
   * make a copy of the all the dragons in the list
   * @return return the new copy list
   */ 
  public ArrayList<Dragon> copyList (){
    ArrayList<Dragon> copyList = new ArrayList<Dragon>();
    java.util.Iterator<Dragon> it = headsDragon.iterator();
    
    while(it.hasNext()){
      copyList.add(it.next());
    }
    return copyList;
  }
  /**
   * 
   */ 
  public void changeAllIteration(int ii){
    int level = ii;
    java.util.Iterator<Dragon> it = headsDragon.iterator();
    while(it.hasNext()){
      
      it.next().changeIteration(level);
      notifyViewrs();
    }
  }
  /**
   * register the viewers to the DrawingBoard
   * @param view the viewrs
   */ 
  public void register (View view){
    viewList.add(view);
    view.iterChanged();
  }
  /**
   * a viewers' notifyer
   * it notifies all viewers to modify their states if any changes occur
   */ 
  private void notifyViewrs (){
    for(int i = 0; i < viewList.size(); i++){
      viewList.get(i).iterChanged();
    }
  }
}