/*
Two linked lists (simple link, not double link) heads are given: headA, and headB;
it is also given that the two lists intersect, thus after the intersection they have the
same elements to the end. Find the first common element, without modifying the lists
elements or using additional datastructures.
a) A linear algorithm is discussed in the lecture: count the lists first, then use the
count difference as an offset in the longer list, before traversing the lists together.
Write a formal pseudocode (the pseudocode in the lecture is vague), using \next" as a
method/pointer to advance to the next element in a list.
b) Write the actual code in a programming language (C/C++, Java, Python etc) of
your choice and run it on a made-up test pair of two lists. A good idea is to use
pointers to represent the list linkage.
 */
package algorithms;

/**
 *
 * @author sarita
 */
public class Algorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        linkedLists A = new linkedLists(); // Two linked lists A and B
        linkedLists B = new linkedLists(); // Below steps add elements to both lists
        A.addElement(5);
        A.addElement(6);
        A.addElement(2);
        A.addElement(3);
        A.addElement(1);
        A.addElement(8);
        A.addElement(9);
        B.addElement(10);
        B.addElement(1);
        B.addElement(0);
        B.addElement(7);
        mergeList(A,B); // this function is the merger function that joins the two lists
        A.displayElements(); // Display elements of list
        B.displayElements();
        findFirstElement(A,B); // Finds first common element of both lists
        
    }
    
    // Merges the two list so that they intersect at a particular node
    public static void mergeList(linkedLists A, linkedLists B)
    {
        Node pointA = A.getHead(), pointB = B.getHead();
        int mergeCount = 0;
        while(mergeCount <3){
            mergeCount++;
            pointA = pointA.next;
        }
        while(pointB.next !=null)
        {
            pointB = pointB.next;
        }
        
        pointB.next = pointA;
    }
    
    // Function that retrieves the first common element in both list
    public static void findFirstElement(linkedLists list1, linkedLists list2){
        int lengthA = list1.calLength();
        int lengthB = list2.calLength();
        int offsetA = 0;
        int offsetB = 0;
        if (lengthA > lengthB)
            offsetA = lengthA - lengthB;
        else
            offsetB = lengthB - lengthA;
        while(offsetA < lengthA){
            if(list1.getData(offsetA).data == list2.getData(offsetB).data){
                System.out.println("First Common Element is -> " + list1.getData(offsetA).data);
                break;
            }
            offsetA++;
            offsetB++;    
        }
        
    }
}
    
// A Node data structure consisting of a data element and a next pointer
class Node{
        int data;
        Node next;
    
    public Node(int data, Node next){
        this.data = data;
        this.next = next;
    }     
}
// A class for linkedlist with a class element as header node, head
class linkedLists{
    private Node head;
    
    public linkedLists(){
        head = null;
    }
    
    public Node getHead()
    {
        return head;
    }
    // Function that adds element to the list
    public void addElement (int d){
        
        Node newnode = new Node(d,null);
        if (head==null)
            head = newnode;
        else
        {
            Node tempnode = head;
            while(tempnode.next != null)
                tempnode = tempnode.next;
            tempnode.next = newnode;
        }
    }
    // Function that displays elements of a list
    public void displayElements(){
        System.out.println("Elements of the list are ->");
        Node tempnode = head;
        while (tempnode!= null){
            System.out.println(tempnode.data +"\t");
            tempnode = tempnode.next;
        }
    }
    // Calculates the length of a list
    public int calLength(){
        int len = 0;
        Node tempnode = head;
        while(tempnode!= null){
            len++;
            tempnode = tempnode.next;
        }
        return len;
    }
    // Gets the header node of a linkedlist
    public Node getData(int position){
        int counter = 0;
        Node tempnode = head;
        while(counter< position){
            tempnode = tempnode.next;
            counter++;
        }
        return tempnode;
    }
}