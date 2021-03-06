/**
 * 
 * @author Duong Chau
 * @version Hw7
 */ 

public class ExpressionTree {
  
  private BTNode root;
  
  /**
   * constructor
   * initializes the tree with the given expression
   * @param s the expression string
   */ 
  public ExpressionTree( String s) {
    s = removeSpace(s);
    root = buildTree(s);
    setParent(root);
  }
  /**
   * private method that build the tree
   * @param s the expression string
   * @return the root node
   */ 
  private BTNode buildTree (String s){
    int count = 0;
    BTNode node = new BTNode();
    if(s.length() == 1){
      node.item = s;
    }
    for(int i = 0; i < s.length() && node.item == null; i++){
      String compare = Character.toString(s.charAt(i));
      if(compare.equals("(")){
          count ++;
          continue;
      }
      if(compare.equals(")")){
          count --;
          continue;
      }
      if(count == 1 && isOperator(compare)){
        node.item = compare;
        node.left = buildTree(s.substring(1,i));
        node.right = buildTree(s.substring(i+1,s.length()));
      }
    }
    return node;
  }
  /**
   * private method that verify if a string is a operator
   */ 
  private boolean isOperator (String s){
    String[] array = {"+","-","/","*"};
    boolean found = false;
    for (int i = 0; i < array.length; i++){
      if(s.equals(array[i]))
        found= true;
    }
    return found;
  }
  /**
   * private helper method that remove all space in the expression
   * @param s the expression string
   * @return  a expression string without spacing.
   */ 
  private String removeSpace (String s ){
    String result = "";
    for(int i = 0; i < s.length(); i++){
      String str = Character.toString(s.charAt(i));
      if( (str == null) || (str.trim().length() == 0) )
        result += str;
    }
    return result;
  }
  /**
   * private method that set the parent node
   * @param the root of a tree
   */
  public void setParent(BTNode r){
    if(r == null)
      return;
    
    if(r.left != null)
      r.left.parent = r;
    if(r.right != null)
      r.right.parent = r;
    
    setParent(r.left);
    setParent(r.right);
  }
  /**
   * Return the root node of this ExpressionTree
   * @return the root node
   */
  public Position root(){
    return root;
  }
  /**
   * Return the calculated value of this ExpressionTree
   * @return the int type value
   */ 
  public int value(){
    int result = 0;
    return result += compute(root);
  }
  /**
   * helper method the compute the value of the subtrees
   * @param n the root of a subtrees
   * @return an int type value
   */ 
  private int compute(BTNode n){
    int r = 0;
    if(n == null){
      throw new IllegalArgumentException();
    }
    if(n.left != null && n.right != null){
      if(n.left.isLeaf() && n.right.isLeaf()){
        int L = Integer.parseInt(n.left.item);
        int R = Integer.parseInt(n.right.item);
        if(n.isAdd()) r = L + R;
        if(n.isMinus()) r = L - R;
        if(n.isMultiply()) r = L * R;
        if(n.isDivide()) r = L / R;
      }
      if(n.left.isLeaf() && !n.right.isLeaf()){
        int L = Integer.parseInt(n.left.item);
        if(n.isAdd()) r = L + compute(n.right);
        if(n.isMinus()) r = L - compute(n.right);
        if(n.isMultiply()) r = L * compute(n.right);
        if(n.isDivide()) r = L / compute(n.right);
      }
      if(!n.left.isLeaf() && n.right.isLeaf()){
        int R = Integer.parseInt(n.right.item);
        if(n.isAdd()) r = R + compute(n.left);
        if(n.isMinus()) r = R - compute(n.left);
        if(n.isMultiply()) r = R * compute(n.left);
        if(n.isDivide()) r = R / compute(n.left);
      }
    }
    return r;
  }
  /**
   * 
   */ 
  public static void main(String args[])
  {        
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        ViewTree viewtree =  new ViewTree() ;
      }
    });    
  }
  class BTNode implements Position{
    String item;
    BTNode left, right, parent;
  
    /**
     * returns the elment stored in this Position.
     * @return this element
     */ 
    public Object element() {
      return item;
    }
    /**
     * returns the parent of this Position. 
     * Return null if no parent exists.
     * @return the parent
     */ 
    public Position parent() {
      return parent;
    }
    /**
     * returns the left child of this Position. 
     * Return null if no left child exists.
     * @return the leftChild
     */ 
    public Position leftChild(){
      if(left == null)
        return null;
      return left;
    }
    /**
     * returns the right child of this Position. 
     * Return null if no right child exists.
     * @return the rightChild
     */
    public Position rightChild(){
      if(right == null)
        return null;
      return right;
    }
    /**
     * helper methods that check if the node is a leaf or a operator
     * @param n a node
     * @return true or false
     */ 
    public boolean isLeaf(){
      return (left == null && right == null);  
    }
    public boolean isAdd(){
      return item.equals("+");
    }
    public boolean isMinus(){
      return item.equals("-");
    }
    public boolean isMultiply(){
      return item.equals("*");
    }
    public boolean isDivide(){
      return item.equals("/");
    }
  }
}