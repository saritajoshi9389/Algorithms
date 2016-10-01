/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
///package general_qs;

/**
 *
 * @author Sharmo
 */
public class palindrome {
    
    public static void main (String args[]){
        
        String s = "121";
        System.out.println("Given String is ::: "+s);        
        palindrome obj = new palindrome();
        System.out.println(obj.isPalindrome(s));
    }
    
    
    public boolean isPalindrome (String str){
        
        int len = str.length();
        int maxLoopCounter = len/2;
        int i =0;
        while (i<maxLoopCounter){
            //System.out.println(str.charAt(i) + " :::: "+str.charAt(len-1-i));
            if (str.charAt(i)!=str.charAt(len-1-i))
                return false;
            i ++;
        }
        return true;
    }
    
}
