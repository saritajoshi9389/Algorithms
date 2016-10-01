/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

/**
 *
 * @author Sharmo
 */
public class Height_Tree {
    
    public int getHeight (Node<?> root){
        int height = 0;
        
        // height of an empty tree is always 0
        if (root == null)
            return -1;
        int leftHeight=0 , rightHeight=0;
        
        //System.out.println("LH  b4 :: "+leftHeight + " ---- "+root.data);
        leftHeight = getHeight(root.left);
        //System.out.println("LH  after :: "+leftHeight + " ---- "+root.data);
        
       // System.out.println("RH  b4 :: "+rightHeight + " ---- "+root.data);
        rightHeight = getHeight(root.left);
        // System.out.println("RH  after :: "+rightHeight + " ---- "+root.data);
        
        
        return Math.max(leftHeight, rightHeight )+1;
    }
    
 public static void main(final String[] args)
 {
  Node<Integer> one = new Node<Integer>(1);
  Node<Integer> two = new Node<Integer>(2);
  Node<Integer> three = new Node<Integer>(3);
  Node<Integer> four = new Node<Integer>(4);
  Node<Integer> five = new Node<Integer>(5);
  Node<Integer> six = new Node<Integer>(6);
  Node<Integer> seven = new Node<Integer>(7);
  Node<Integer> eight = new Node<Integer>(8);
  Node<Integer> nine = new Node<Integer>(9);
  one.setLeft(two);
  one.setRight(three);
  two.setLeft(four);
  two.setRight(five);
  three.setLeft(six);
  four.setLeft(seven);
  six.setLeft(eight);
  six.setRight(nine);
  
  Height_Tree obj = new Height_Tree();
  int height = obj.getHeight(one);
     System.out.println(" FINAL HEIGHT :::: " + height);
}
}
