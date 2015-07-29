import java.awt.*;
import java.util.Iterator;


/**
 * a class that implements the following public interface 
 * to hold a collection of Point objects 
 * 
 * @author Duong Chau
 * @version HW6
 */ 

public class Path {
  //instance variable
  private PointNode tail; //the tail of the list
  private int size;       //the size of the list  
  
  /**
   * constructor
   * construct an empty Path
   */ 
  public Path(){
    size = 0;
    tail = null;
  }
  /**
   * returns true if this Path contains p; false if not.
   * @param p a Point
   * @return a boolean type true or false
   */ 
  public boolean contains (Point p){
    boolean found = false;
    PointNode help = tail.next;
    for(int i = 0; i < size && !found; i++){
      if(help.item.equals(p))
        found = true;
      help = help.next;
    }
    return found;
  }
  /**
   * returns true if this Path is empty; false if not
   * @return a boolean type true or false
   */ 
  public boolean isEmpty(){
    return size == 0;
  }
  /**
   * a private helper method to calculate the distance between
   * 2 points
   * @param p1 1st point 
   * @param p2 2nd point
   * @return double type distance
   */ 
  private double distance_calc (Point p1, Point p2){
    return
      Math.sqrt( Math.pow((p2.getX() - p1.getX()),2) + Math.pow((p2.getY() - p1.getY()),2));
  }
  /**
   * returns the total distance traveled going from the first Point
   * to the last Point. If the Path has <=1 Point, return 0.
   * @return a double type distance
   */ 
  public double totalDist(){
    double distance = 0;
    
    if(size <= 1)
      return 0;
    
    PointNode help = tail.next;
    for(int i = 0; i < size - 1; i++){
      Point temp1 = help.item;
      Point temp2 = help.next.item;
      distance += distance_calc(temp1,temp2);
      
      help = help.next;
    }
    return distance;
  }
  /**
   * reverse the order of the Points in this Path. 
   * This method should not make any new nodes
   */ 
  public void reverse(){
    PointNode helper1 = tail; //holds the node move from
    PointNode helper2 = tail; //holds the node move to   
    PointNode head = tail;    //holds the head node 
    tail = tail.next;         //tail gets move to the first node
    
    //find the second last node for the helper2 to holds on
    for(int i = 0; i < size-1; i++){
      helper2 = helper2.next;
    }
    //reversing the order of the nodes
    for(int i = 0; i < size-1; i++){
      helper1.next = helper2;
      helper1 = helper1.next;
      helper2 = helper2.next;
    }
    tail.next = head; //links the tail to the head to make a circular list
    
  }
  /**
   * append p to the end of the Path.
   * @param p a new point that will be added
   */ 
  public void append(Point p){
    //make a new node with the point passed in
    PointNode newPoint = new PointNode(p,null);
    if(tail == null){
      tail = newPoint;
      newPoint.next = tail;
    }else{
      //link new node to the head
      newPoint.next = tail.next;
      //link tail to new node
      tail.next = newPoint;
      //set tail to the new node
      tail = newPoint;
    }
  }
  /**
   * remove the first instance of p from the Path 
   * and return true. If the Point doesn't exist, return false.
   * @param p the point that got compared to
   */ 
  public boolean remove(Point p){
    boolean rmove = false;
    
    if(size == 1){
      if(tail.item.equals(p)){
        rmove = true;
        tail = null;
        size --;
      }
    }
    else if(size > 1){
      PointNode temp = tail;
      for(int i = 0; i < size && !rmove; i++){
        if(temp.next.item.equals(p)){
          rmove = true;
          temp.next = temp.next.next;
          size --;
        }
        temp = temp.next;
      }
    }
    return rmove;
  }
  /**
   * removes all Points from this Path other than the Point p. 
   * This could result in an empty Path. Return how many values 
   * were removed.
   * @param p the point or points that will be left on the path
   * @return int type number of points that removed
   */ 
  public int removeAllExcept(Point p){
    int count = 0;
    PointNode help = tail;
    for(int i = 0; i < size; i++){
      if(!help.next.item.equals(p)){
        remove(help.next.item);
        count++;
      }
    }
      return count;
  }
  /**
   * Splits this Path into two Paths based on the parameter
   * 
   * @param coordinate, decides which Path going to be splited
   * @return the Path that got splited
   */ 
  public Path split(boolean coordinate) {
    Path splited = new Path();
    PointNode help = tail;
    if(coordinate){
      int count = 0; //count the size of the new path
      for(int i = 0; i < size; i++){
        //a case when tail is the negative value
        //let the tail points to another node that does not
        //have the negative value.
        if(tail.item.getX() < 0){
          PointNode h = tail;  //the helper node that points to the node before tail;
          PointNode head = null;
          
          for( int ii = 0; ii < size-1; ii++){
            h = tail.next;
          }
          for( int j = 0 ; j < size && tail.item.getX() < 0; j++){
            if(splited.tail == null){
              //tail and head of new path = tail of current path
              splited.tail = tail;
              head = tail;
              //tail points to the next node
              tail = tail.next;
              //next of tail points to itself
              splited.tail.next = splited.tail;
              //link the h to the new tail to make the circular system.
              h.next = tail;
              help = tail;
            }else if(splited.tail != null){
              splited.tail.next = tail;
              tail = tail.next;
              splited.tail.next.next = head;
              splited.tail = splited.tail.next;
              h.next = tail;
              help = tail;
            }
            count ++;
          }
          for( int j = count ; j < size; j ++){
            if(help.next.item.getX() < 0){
              splited.tail.next = help.next;
              help.next = help.next.next;
              splited.tail.next.next = head;
              splited.tail = splited.tail.next;
            }
          }
        } else if(tail.item.getY() < 0){
          
        }
        else {
          if(help.next.item.getX() < 0){
            //helper note that points to the head of the new path
            PointNode head = null;
            if(splited.tail == null){
              //tail.next points to the negative X Point
              splited.tail = help.next;
              //links help.next to the next of help.next
              help.next = help.next.next;
              //move the tail to the new added Point
              splited.tail.next = splited.tail;
              head = splited.tail;
            } else if(splited.tail != null){
              //tail.next points to the negative X Point
              splited.tail.next = help.next;
              //links help.next to the next of help.next
              help.next = help.next.next;
              //the new node tail.next points to now point back to the tail
              //making circular
              splited.tail.next.next = head;
              //move the tail to the new added Point
              splited.tail = splited.tail.next;
            }
            
          }
          
        }
      }
    }
    else{
      int count = 0;
      for(int ii = 0; ii < size; ii++){
        //a case when tail is the negative value
        //let the tail points to another node that does not
        //have the negative value.
        if(tail.item.getX() < 0){
          for(int j = 0; j <size && tail.item.getX() < 0; j++){
            tail = tail.next;
            help = tail;
          }
        }
        if(help.next.item.getY() < 0){
          if(splited.tail == null){
            //tail.next points to the negative Y Point
            splited.tail = help.next;
            //links help.next to the next of help.next
            help.next = help.next.next;
            //move the tail to the new added Point
            splited.tail.next = splited.tail;
          } else if(splited.tail != null){
            //helper note that points to the head of the new path
            PointNode head = splited.tail;
            //tail.next points to the negative Y Point
            splited.tail.next = help.next;
            //links help.next to the next of help.next
            help.next = help.next.next;
            //the new node tail.next points to now point back to the tail
            //making circular
            splited.tail.next.next = head;
            //move the tail to the new added Point
            splited.tail = splited.tail.next;
          }
        }
        count ++;
        splited.size = count;
      }
    }
    return splited;
  }
  
  /**
   * Override the toString method. Returns a String of all the values
   * in this Path. Each value should be separated by a '\n' character. 
   * There should not be a '\n' at the end of the String.
   * 
   * @return a String of all the values in this Path
   */ 
  public String toString(){
    PointNode helper = tail;
    String str = "";
    for(int i = 0; i < size; i++){
      if(str == ""){
        str += "( " + helper.item.getX() + " , " + helper.item.getY() + " )";
      } else {
        str += "\n( " + helper.item.getX() + " , " + helper.item.getY() + " )";
      }
      helper= helper.next;
    }
    return str;
  }
  /**
   * Returns a String of all the values in the Path, 
   * starting at Point p
   * 
   * @param p a Point that this method starts to 
   * add points in a string
   * @return a String of all the values in this Path
   */ 
  public String toString(Point p){
    String str = "";
    //return an empty string if does not contain p
    if(!contains(p))
      return str;
    
    //find a starting point
    //and the number of points going to be added
    PointNode helper = tail.next;
    int count = 0; //a number that will be substract by the size
    for(int i = 0; i < size; i++){
      if(!helper.item.equals(p)){
        count ++;
        helper = helper.next;
      }
    }
    //get the number of points going to be added
    //and add those points to the string
    int newsize = size - count;
    for(int ii = 0; ii < newsize; ii++){
      if(str == ""){
        str += "( " + helper.item.getX() + " , " + helper.item.getY() + " )";
      } else {
        str += "\n( " + helper.item.getX() + " , " + helper.item.getY() + " )";
      }
      helper = helper.next;
    }
    return str;
  }
  /**
   * Override the equals() method.
   * Returns true if this Path and the parameter 
   * contain the same Points in the same order.
   * 
   * @param o Object compare with
   * @return boolean type true or false
   */ 
  public boolean equals(Object o){
    boolean equal = true;
    if(o == null){
      equal = false;
    }else if(o.getClass() != this.getClass()){
      equal = false;
    }else{
      Path name = (Path) o;
      if (name.size != size){
        equal = false;
      }else{
        PointNode helper1 = tail;
        PointNode helper2 = name.tail;
        for(int i = 0; i < size; i++){
          if(!helper1.item.equals(helper2.item)){
            equal = false;
          }
          helper1 = helper1.next;
          helper2 = helper2.next;
        }
      }
    }
    return equal;
  }
  /**
   * Creates a new Path that has only the Points that 
   * are contained in both this Path and in other. 
   * This Path and the other Path should not be changed.
   * 
   * @param other the path that use to compare
   * @Path the path that has the intersection points
   */
  public Path intersection(Path other){
    Path intersect = new Path();
    PointNode helper1 = tail;
    for(int i = 0; i < size; i++){
      PointNode helper2 = other.tail;
      for(int j = 0; j < other.size; j++){
        if(helper1.item.equals(helper2.item)){
          intersect.append(helper2.item);
        }
        helper2 = helper2.next;
      }
      helper1 = helper1.next;
    }
    return intersect;
  }
  /**
   * iterator
   * @return an Iterator object over this Path
   */
  public Iterator<Point> iterator(){
    return new InsideIterator();
  }
  
  /**
   * a private iterator class
   */ 
  private class InsideIterator  implements Iterator<Point>{
    
    private PointNode helper;
    private int limit;
    
    public InsideIterator(){
      helper = tail.next;
      limit = 1;
    }
    
    public boolean hasNext(){
      while(limit <= size){
        limit ++;
        return helper != null;
      }
      return false;
    } 
    
    public Point next(){
      Point temp =  helper.item;
      helper = helper.next;
      return temp;
    }
    
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
}