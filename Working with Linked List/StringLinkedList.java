import java.util.Iterator;
/**
 * Write a description of class StringLinkedList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StringLinkedList
{
    private StrNode head, tail;
    private int size;
    /**
     * Constructor for objects of class StringLinkedList
     */
    public StringLinkedList()
    {
        head = null;
        tail = null;
        size = 0;
    }

    public StringLinkedList(StringLinkedList orig){
        for (StrNode it = orig.head; it != null; it=it.next){
            add(it.item);
        }
    }

    public int size(){
        return size;
    }

    public void clear(){
        size = 0;
        head = null;
        tail = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    private void testPos(int pos){
        if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException();
    }

    // precondition: pos has already been validated: 0 <= pos < size
    private StrNode getNode(int pos){
        if (pos == size - 1) return tail;
        StrNode temp = head;
        for (int i = 0; i < pos; i++){
            temp = temp.next;
        }
        return temp;
    }

    public String get(int pos){
        testPos(pos);
        return getNode(pos).item;
    }

    public String set(int pos, String value){
        testPos(pos);
        StrNode node = getNode(pos);
        String oldVal = node.item;
        node.item = value;
        return oldVal;
    }

    public boolean contains(String s){
        boolean found = false;
        StrNode helper = head;
        while (helper != null && !found){
            if (helper.item.equals(s))
                found = true;
            helper = helper.next;
        }
        return found;
    }

    // first index of,
    public int indexOf(String s){
        int found = -1;
        StrNode helper = head;
        for (int i = 0; i <size && found == -1; i++){
            if (helper.item.equals(s))
                found = i;
            helper = helper.next;
        }
        return found;
    }

    public String remove(int pos){
        testPos(pos);
        String s = getNode(pos).item;
        if (size == 1){
            head = null;
            tail = null;
        }
        else if (pos == 0){
            head = head.next;
        }
        else if (pos == size - 1){
            tail = getNode(pos - 1);
            tail.next = null;
        }
        else{
            StrNode temp = getNode(pos-1);
            temp.next = temp.next.next;
        }
        size--;
        return s;
    }

    public void add(int pos, String s){
        if (pos > size || pos < 0) throw new IndexOutOfBoundsException();
        if (pos == size) add(s);
        else{
            StrNode temp = new StrNode(s, null);
            if (pos == 0){
                temp.next = head;
                head = temp;
            }
            else{
                StrNode helper = head;
                for (int i = 0; i < pos - 1; i++){   // instead of call to getNode
                     helper = helper.next;
                }
                temp.next = helper.next;
                helper.next = temp;
            }
            size++;
        }
    }

    public boolean add(String s){
        StrNode last = new StrNode(s, null);
        if (size == 0){
            head = last;
            tail = head;
        }else{
            tail.next = last;
            tail = last;
        }
        size++;
        return true;
    }

    
    public Iterator<String> iterator(){
        return new InsideIterator();
    }
    private class InsideIterator  implements Iterator<String>{
       // because this class definition is a member of the IntArrayList class
       // it has direct access to the instance variables of IntArrayList class
       
        private StrNode helper;
       
       public InsideIterator(){
           helper = head;
        }
        
        public boolean hasNext(){
            return helper != null;
        }
        
        public String next(){
            String temp =  helper.item;
            helper = helper.next;
            return temp;
        }
        
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }


    class StrNode {
        String item;       // string associated with this node
        StrNode next;  // link to next StrNode, or null if no next node
        StrNode(String data, StrNode n){
            item = data;
            next = n;
        }
    }
	
    public static StrNode removeEmpty(StrNode front){
        StrNode out = null;
        if (front != null){
            if (front.item.equals(""))
                out = removeEmpty(front.next);
            else{
                front.next = removeEmpty(front.next);
                out = front;
            }
        }
        return out;
    }

    public static void printInOrder3(StrNode n){
        if (n != null){
            System.out.println(n.item);
            printInOrder3(n.next);
        }
    }

    public static void printInOrder(StrNode n){
        StrNode hlp = n;   // or could use n

        while (hlp != null){
            System.out.println(hlp.item);
            hlp = hlp.next;
        }
    }

    public static void printInOrder2(StrNode n){
        StrNode hlp = n;   // or could use n
        if (hlp != null){
            System.out.println(hlp.item);
            while (hlp.next != null){
                hlp = hlp.next;
                System.out.println(hlp.item);

            }
        }
    }

    public static void printReverseOrder(StrNode n){
        if (n != null){
            printReverseOrder(n.next);
            System.out.println(n.item);

        }
    }

}
