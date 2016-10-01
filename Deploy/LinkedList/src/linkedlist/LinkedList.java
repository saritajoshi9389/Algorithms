
package linkedlist;

/**
 *
 * @author Sharmo
 */
public class LinkedList {

    /**
     * @param args the command line arguments
     */
    
    //public static Node head;
    
    public void printList (Node h){
        //System.out.println("inside print element");
        System.out.println("Current Contents of the LinkedList are ::");
        Node tmpNode = h;
        while (tmpNode != null){
            System.out.print("  "+tmpNode.data+"\t");
            tmpNode = tmpNode.next;
        }
        System.out.println("");
        
    }
    
    // Returns head of the list
    public Node addElement (int d, Node h) { 
        //System.out.println("inside add element");
        Node newNode = new Node(d,null);
        if (h == null){
            h = newNode;
            return h;
        }
        else {
            Node tmpNode = h;
            while(tmpNode.next != null){
                tmpNode = tmpNode.next;    
            }
            tmpNode.next = newNode;    
            return h;
        }
    }
    
}
