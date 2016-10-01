/*  BINOMIAL HEAP IMPLEMENTATION
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binomialheap;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.System.exit;
import java.util.Scanner;

/**
 *
 * @author sarita joshi
 */

// Class for Nodes in a Binomial Heap
class Node {
    int key; 
    int degree;
    Node p;
    Node sibling;
    Node child;

    public Node(int k) {
        key = k;
        degree = 0;
        p = null;
        sibling = null;
        child = null;
    }
    // Function to find the Minimum value
    public Node findMin() {
        Node N1 = this;
        Node N2 = this;
        int minNode = N1.key;
        while (N1 != null) {
            if (N1.key < minNode) {
                N2 = N1;
                minNode = N1.key;
            }
            N1 = N1.sibling;
        }
        return N2;
    }
    
    // Recursive program to get the Size of the Binomial Heap
    public int getSize() {
        return (1 + ((sibling == null) ? 0 : sibling.getSize()) + ((child == null) ? 0 : child.getSize()));
    }
    // Initialization
    public void setInitial(int k) {
        key = k;
        degree = 0;
        p = null;
        sibling = null;
        child = null;
    }
    // Returns a node with particular value in the Binomial Heap
    public Node findSpecificNode(int val) {
        Node temp = this;
        Node node = null;
        // Recurses through the entire binomial heap
        while (temp != null) {
            // If match found
            if (temp.key == val) {
                node = temp;
                break;
            }
            if (temp.child == null)
                temp = temp.sibling;
            else {
                node = temp.child.findSpecificNode(val);
                if (node == null)
                    temp = temp.sibling;
                else
                    break;
            }
        }
        return node;
    }
    
    // Reverse function for the Binomial Node
    public Node reverseItem(Node s) {
        Node reverse;
        if (sibling == null)
            reverse = this;   
        else
            reverse = sibling.reverseItem(this);
        sibling = s;
        return reverse;
    }
        
}
// Class that implements the Binomial Heap structure
// Attributes - size of the binomial tree and the Node in the Binomial Heap
public class BinomialHeap {
    private int size;
    private Node BNodes;

    // Initialize a BinomialHeap
    public BinomialHeap()
    {   
        size = 0;
        BNodes = null;
        
    }
    // Returns size of Binomial Heap
    public int getSize() 
    {
        return size;
    }
    
    public Node getBNodes()
    {
        return BNodes;
    }
    
    // Get the node with Minimum key value
    public int getMinimum(){
        return BNodes.findMin().key;
    }
    
    // Making a BHeap Empty
    public void makeEmpty()
    {
        BNodes = null;
        size = 0;
    }
    // make-heap function
    
    public void make_heap(int size){
        Node bh = new Node(size);
        bh.setInitial(size);
    }
    // Function to insert a element to a Binomial Heap
    public void insert(int val) {
        if (val > 0) {
            Node temp = new Node(val);
            if (BNodes == null) {
                BNodes = temp;
                size = 1;
            }
            else {
                union(temp);
                size++;
            }
        }
    }
    
    // Function that deletes an element from a B Neap
    public void delete(int val) {
        if ((BNodes != null) && (BNodes.findSpecificNode(val) != null)) {
            decreaseKey(val, getMinimum() - 1);
            extractMin();
        }
    }
    
    // Function that decreases the Key already present in the B Heap by the given value
    public boolean decreaseKey(int val1, int val2) 
    {
        if (val1 < val2) {
            System.out.println ("Incorrect entry!!! Value should be less than the original one");
            return false;
        }
         
        Node temp = BNodes.findSpecificNode(val1);
        if (temp == null){
            System.out.println (" Given value doesnot exist in the heap");
            return false;
        }           
        temp.key = val2;
        Node tempParent = temp.p;
        while ((tempParent != null) && (temp.key < tempParent.key)) 
        {
            int tmp = temp.key;
            temp.key = tempParent.key;
            tempParent.key = tmp;
            temp = tempParent;
            tempParent = tempParent.p;
        }
        
        return true;
    }
    // Function that Merges binomial Heaps based on the 4 cases of merge based on mumber of degrees  
    private void merge(Node n) {
        Node temp1 = BNodes, temp2 = n;

        while ((temp1 != null) && (temp2 != null)) {
            if (temp1.degree == temp2.degree) {
                Node tmp = temp2;
                temp2 = temp2.sibling;
                tmp.sibling = temp1.sibling;
                temp1.sibling = tmp;
                temp1 = tmp.sibling;
            }
            else {
                if (temp1.degree < temp2.degree) {
                    if ((temp1.sibling == null) || (temp1.sibling.degree > temp2.degree)) {
                        Node tmp = temp2;
                        temp2 = temp2.sibling;
                        tmp.sibling = temp1.sibling;
                        temp1.sibling = tmp;
                        temp1 = tmp.sibling;
                    }
                    else {
                        temp1 = temp1.sibling;
                    }
                }
                else {
                    Node tmp = temp1;
                    temp1 = temp2;
                    temp2 = temp2.sibling;
                    temp1.sibling = tmp;
                    if (tmp == BNodes) {
                        BNodes = temp1;
                    }
                }
            }
        }
        if (temp1 == null)
        {
            temp1 = BNodes;
            while (temp1.sibling != null)
            {
                temp1 = temp1.sibling;
            }
            temp1.sibling = temp2;
        }
    }
    // Function union that internally calls a Binomial Merge operation for the given binomial Heap
    public void union(Node n) {
        merge(n);
        Node prev = null;
        Node temp = BNodes;
        Node next = BNodes.sibling;
        while (next != null) {
            if ((temp.degree != next.degree) || ((next.sibling != null) && (next.sibling.degree == temp.degree))) {
                prev = temp;
                temp = next;
            }
            else {
                if (temp.key <= next.key) {
                    temp.sibling = next.sibling;
                    next.p = temp;
                    next.sibling = temp.child;
                    temp.child = next;
                    temp.degree++;
                }
                else {
                    if (prev == null) {
                        BNodes = next;
                    }
                    else {
                        prev.sibling = next;
                    }
                    temp.p = next;
                    temp.sibling = next.child;
                    next.child = temp;
                    next.degree++;
                    temp = next;
                }
            }
            next = temp.sibling;
        }
    }

    /* Function to extract the node with the minimum key */
    public int extractMin() {
        if (BNodes == null)
            return -1;

        Node temp = BNodes;
        Node prev = null;
        Node minNode = BNodes.findMin();

        while (temp.key != minNode.key) {
            prev = temp;
            temp = temp.sibling;
        }

        if (prev != null) {
            
            prev.sibling = temp.sibling;
        }
        else {
            
            BNodes = temp.sibling;
        }

        temp = temp.child;
        Node falseNode = temp;
        while (temp != null) {
            temp.p = null;
            temp = temp.sibling;
        }
        if ((BNodes == null) && (falseNode == null)) {
            size = 0;
        }
        else {
            if ((BNodes == null) && (falseNode != null)) {
                BNodes = falseNode.reverseItem(null);
                size = BNodes.getSize();
            }
            else
            {
                if ((BNodes != null) && (falseNode == null))
                {
                    size = BNodes.getSize();
                }
                else
                {
                    union(falseNode.reverseItem(null));
                    size = BNodes.getSize();
                }
            }
        }
        return minNode.key;
    }
    
    public void displayBinomialHeap(){
        System.out.print("\n Heap is -> : ");
        displayBinomialHeap(BNodes);
        System.out.println("\n");
    }
    private void displayBinomialHeap(Node n)
    {
        if (n != null){
            displayBinomialHeap(n.child);
            System.out.print(n.key +"\t");
            displayBinomialHeap(n.sibling);
        }
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinomialInteraction a = new BinomialInteraction();
        a.implementBinomialHeap();
        
    }
    
}

class BinomialInteraction{
    
    public void implementBinomialHeap(){
        
        Scanner s = new Scanner(System.in);
        System.out.println("Binomial Heap Implementation\n");
        BinomialHeap bh = new BinomialHeap();
        char getInput='y';
        while(getInput=='y'|| getInput=='Y'){
            System.out.println("Enter the option you want to operate ->\n");
            System.out.println("1 -> INSERT ");
            System.out.println("2 -> DELETE ");
            System.out.println("3 -> HEAP SIZE");            
            System.out.println("4 -> DECREASE KEY");
            System.out.println("5 -> GET MINIMUM");
            System.out.println("6 -> EXTRACT MINIMUM");
            System.out.println("7 -> CLEAR BHEAP");
            System.out.println("8 -> UNION BHEAPs 1) One BHeap can be drawn random using user interaction and one Bheap is read from file \n");
            System.out.println("9 -> EXIT");
            int ch = s.nextInt();
            switch(ch){
                case 1: 
                    System.out.println("Enter the value to be inserted \n");
                    int val = s.nextInt();
                    bh.insert(val);
                    System.out.println("Value" + val + "Inserted!!");
                    break;
                case 2:
                    System.out.println("Enter the key to be deleted \n");
                    val = s.nextInt();
                    bh.delete(val);
                    System.err.println("Value " + val + "deleted!!");
                    break;
                case 3: 
                    System.out.println("Heap Size is  -> :: " + bh.getSize());
                    break;
                case 4:
                    System.out.println("Enter the key to be decreased, existing one \n");
                    int val1 = s.nextInt();
                    System.out.println("Enter the new key value which should be less than the existing one \n");
                    int val2 = s.nextInt();
                    if (bh.decreaseKey(val1,val2))
                        System.out.println(" SUCCESS!!!!");
                    else
                        System.out.println("FAILED !!");
                    break; 

                case 5: 
                    System.out.println("The minimum value is -> :: \n" + bh.getMinimum());
                    break;
                case 6:
                    System.out.println("Extracting the minimum value is -> :: \n" + bh.extractMin());
                    break;
                case 7:
                    bh.makeEmpty();
                    System.out.println("Heap is cleaned !!! \n");
                    break;
                case 8:
                    System.out.println(" Merging two B heaps, created from value stored in Input.txt \n");
                    String dir = "Input.txt";
                    Scanner sc1 = null;
                    String data = null;
                    BinomialHeap bh1 = new BinomialHeap();
                    try {
                            sc1 = new Scanner(new File(dir));
                            sc1.useDelimiter("\\A");
                            data = sc1.next();
                        
                        } catch (FileNotFoundException e) {
                            System.out.println("Error, Please check dir path");
                            e.printStackTrace();
                        }finally
                            {
                                sc1.close();
                            }
                    String[] words = data.split(" ");
                    for(int i =0;i<words.length;i++){
            
                    if(words[i]!=null)
                    { 
                        bh1.insert(Integer.parseInt(words[i]));
                    }
            
                    }
                    bh.union(bh1.getBNodes());
                    System.out.println("Merged Binomial Heap is -> :: \n");
                    break;
                case 9:
                    exit(0);
                default:
                    exit(0);
                    
                    
            }
            bh.displayBinomialHeap();
            System.out.println("Do you want to continue? Type y/Y or n/N");
            getInput = s.next().charAt(0);        
            
        }
        
    }
    
    
}


