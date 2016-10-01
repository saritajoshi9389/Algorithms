/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sharmo
 */
public class Word_Anagram {
    
    public static void main(String args[]){
        
        String s1 = "abfcm" , s2 = "ecam" ;
        Word_Anagram obj = new Word_Anagram();
        boolean result = obj.isAnagram(s1, s2);
        System.out.println(result);
    }
    
    /*
    Method 1 ::: Fastest algorithm would be to map each of the 26 English characters to a unique prime number. 
            Then calculate the product of the string. By the fundamental theorem of arithmetic, 
            2 strings are anagrams if and only if their products are the same.
    */
    
    // Method 2 :::: 
    public boolean isAnagram (String s1 , String s2){
        
        int uniqueCharArr [] = new int[256];  //  There are 256 unique charatrs in ASCII
        // filling all the elements with 0 ..........
        // java.util.Arrays has fill method
        java.util.Arrays.fill(uniqueCharArr,0);
        
        if (s1.length() != s2.length())
            return false;
        
        int asciiVal;
        int i =0;
        while (i<=s1.length()-1){
            asciiVal = s1.charAt(i);
            uniqueCharArr[asciiVal]++;
            i++;
        }
        
        i=0;
        while (i<=s2.length()-1){
           asciiVal = s2.charAt(i);
           uniqueCharArr[asciiVal] --;
           if(uniqueCharArr[asciiVal]<0)
               return false;
           i++;
        }
        return true;
        
        
    }
    
}
