/*
 Two linked lists (simple link, not double link) heads are given: headA, and headB;
it is also given that the two lists intersect, thus after the intersection they have the
same elements to the end. Find the first common element, without modifying the lists
elements or using additional datastructures
 */
package linkedlist;
/**
 *
 * @author Sharmo
 */
public class AlgoAssgn1 {
    
    Node headA = null;
    Node headB = null;
    
    public static void main (String args[]){
        AlgoAssgn1 obj = new AlgoAssgn1();
        obj.make2LinkedLists();
    }
    
    public void make2LinkedLists(){
        LinkedList obj = new LinkedList();
        headA = obj.addElement(10, headA);
        headA = obj.addElement(11, headA);
        headA = obj.addElement(15, headA);
        headA = obj.addElement(8, headA);
        
        headB = obj.addElement(81, headB);
        headB = obj.addElement(18, headB);
        headB = obj.addElement(82, headB);
        headB = obj.addElement(23, headB);
        
        // Traversing LisnkedList A till we reach the merging point
        Node pointerA = headA , pointerB = headB;
        int counter = 1;
        while (counter<3){
            counter++;
            pointerA = pointerA.next;
        }
        
        // Trversing till the end of LinkedList B
        while(pointerB.next!=null){
            pointerB = pointerB.next;
        }
        
        // Here LinkedList B is being pointed to the intersection point
        // Both linkd list B and A now point to common elements {15,8}.
        // Thus the required merging point is 15.
        pointerB.next = pointerA;       
  
        obj.printList(headA);
        obj.printList(headB);   
        firstCommonEle();
    }
    
    void firstCommonEle (){
        Node tmpA = headA , tmpB = headB;
        int lenA =0 , lenB = 0;
        while (tmpA != null){
            lenA ++;
            tmpA = tmpA.next;
        }
        while (tmpB != null){
            lenB ++;
            tmpB = tmpB.next;
        }
        System.out.println("Length of First List :: " + lenA);
        System.out.println("Length of Second List :: " + lenB);
        int offSet = Math.abs(lenA-lenB);
        
        tmpA = headA; tmpB = headB;
        int i = 1;
        if (lenA > lenB){
            while (i<=offSet){
                tmpA = tmpA.next;
                i ++;
            }
        }
        else{
            while (i<=offSet){
                tmpB = tmpB.next;
                i ++;
            }
        }
        
        while (tmpA != null){
            if(tmpA.data == tmpB.data){
                System.out.println("The First Common Element is ::: " + tmpA.data);
                break;
            }
            tmpA = tmpA.next;
            tmpB = tmpB.next;
        }
            
    }
    
    
    
}
