import java.awt.*;
/**
 * a PointNode class that store a point as a data
 * this will be used in Path class
 * 
 * @author Duong Chau
 * @version HW6
 */ 

public class PointNode{
  //instance variable
  Point item;
  PointNode next;
  
  PointNode(Point data, PointNode n){
    item = data;
    next = n;
  }
}