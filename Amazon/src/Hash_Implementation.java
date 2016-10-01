/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package general_qs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author Sharmo Sarkar
 */
public class Hash_Implementation {

    public static int HASHTABLE_LEN = 11;
    public static void main(String[] args) {
        String dir = "alice_in_wonderland.txt";
        Scanner scanner = null;
        String textData = null;
        Hashing Hashobject = new Hashing();
        try {
		scanner = new Scanner(new File(dir));
                scanner.useDelimiter("\\A");
                textData = scanner.next();
                        
		} catch (FileNotFoundException e) {
			System.out.println("Error, Please check dir path");
			e.printStackTrace();
		}finally
                    {
			scanner.close();
		}
        StringHandler sh = new StringHandler();
        String[] words = sh.getWords(textData);
        
        String sss[] = {"aaa","bbb","ccc","mmm","ccc"};
        for(int i =0;i<sss.length;i++){            
            if(sss[i]!=null && !sss[i].isEmpty()){
                int key = Hashobject.calcHash(sss[i]) % HASHTABLE_LEN;
                Hashobject.insert(key , sss[i]);
            }
        }
        
        Hashobject.ListAllKeys();
        Hashobject.delete("mmm");
        Hashobject.ListAllKeys();
    }    
}


// Class to handle the given input text file.Here this regex splits for any
// nonword character
class StringHandler
{
    public String[] getWords(String data){
        return data.split("[\\W]");
    }            
}

class Hashing
{
    public static int ANY_PRIME = 31;
    public static int HASHTABLE_LEN = 11;
    public static  Node_LL[] HashTable = new Node_LL[HASHTABLE_LEN];
    public linkedList linkedListObj = new linkedList();
    
    public int calcHash(String str){
        int hash = 0;
        for(int i=0; i< str.length();i++)
            hash += str.charAt(i) * ANY_PRIME;
        return hash;
    }
    
    
    // inserts into HashTable
    public void insert (int key, String val) {        
        if (HashTable[key] == null){
            Node_LL newnode = new Node_LL(val,1,null);
            HashTable[key] = newnode;
        }
        else{
            //search if word is present in the linkedList
            increase (val);
        }
    }
    
    // key is the word which is to be deleted
    // deletes the node which contains the key 
    // if key not found in the list, then error msg printed
    public void delete (String key){
        int pos = calcHash(key) % HASHTABLE_LEN;
        linkedListObj.deleteElement(HashTable[pos], key);
    }
    
    // here the key is the word, whose count is to be increased
    // increases the Key ::::: 
    // if the word is found in the linked list , then count is increased
    // else count is made to 1, and new word is inserted to the LL
    public void increase(String key) {
        int pos = calcHash(key) % HASHTABLE_LEN;
        Node_LL tmpNode = find (key);
        if (tmpNode == null) // word not present in LL
            linkedListObj.addElement(HashTable[pos], key, 1);
        else // word present, so increase count and update the Node
            linkedListObj.updateCount(tmpNode);
    }
    
    // key is the word that is to be searched
    // returns a null node if key is not present in the list
    // else returns the node containing the key
    public Node_LL find(String key){
       int pos = calcHash(key) % HASHTABLE_LEN;
       return linkedListObj.searchElement(HashTable[pos], key);
    }
    
    
    // list all unique words (irespective of the position in the has table)
    public void ListAllKeys(){
        int i =0;
        while (i<HashTable.length){
            linkedListObj.displayWords(HashTable[i]);           
            i++;
        }
    }
    
    public void printHashTable (){
        int i =0;
        while (i<HashTable.length){
            System.out.println("POS "+i+" -> ");
            linkedListObj.displayElements(HashTable[i]);           
            System.out.println();
            i++;
        }
    }
            
}


class linkedList{
    private Node_LL head;
    
    public linkedList(){
        head = null;
    }
    
    // returns Null if word is not in the LL
    public Node_LL searchElement (Node_LL head , String s){
        Node_LL tempnode = head;
        while(tempnode != null){
            if(tempnode.word.equals(s)){
                return tempnode;
            }
            tempnode = tempnode.next;
        }
        return null;
    } 
    
    // delete the specific node from the LinkedList whose head is given
    // returns true if node was found and deleted.
    // returns false if node could not be found (thus not deleted)
    public boolean deleteElement (Node_LL head, String s){
        Node_LL tempnode = head;
        while(tempnode.next != null){
            if(tempnode.next.word.equals(s)){
                tempnode.next = tempnode.next.next;
                return true;
            }
            tempnode = tempnode.next;
        }
        return false;
    }
    
    public void updateCount (Node_LL node){
        node.count += 1;
    }
    
    // Function that adds element to the list
    public void addElement (Node_LL head, String s,int d){
        
        Node_LL newnode = new Node_LL(s,d,null);
        if (head==null){
            head = newnode;
        }
        else
        {
            Node_LL tempnode = head;
            while(tempnode.next != null)
                tempnode = tempnode.next;
            tempnode.next = newnode;
        }
    }
    // Function that displays elements of a list
    public void displayElements(Node_LL head){
        System.out.println("Elements of the list are ->");
        Node_LL tempnode = head;
        while (tempnode!= null){
            System.out.println(tempnode.word +"\t"+ tempnode.count +"\t");
            tempnode = tempnode.next;
        }
    }
    
        // Function that displays elements of a list
    public void displayWords(Node_LL head){
        Node_LL tempnode = head;
        while (tempnode!= null){
            System.out.println(tempnode.word );
            tempnode = tempnode.next;
        }
    }
}

class Node_LL{
        int count;
        Node_LL next;
        String word;
    
    public Node_LL( String word,int data, Node_LL next){
        this.word =word;
        this.count = data;
        this.next = next;
    }     
}