/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sharmo
 */
public class Uniqe_Chars {
    
    public static void main (String args[]){
        
        Uniqe_Chars obj = new Uniqe_Chars();
        String s = "ss";
        obj.using_integer_checker(s);
        
        
    }
    
    // THERE ARE 256 UNIQUE ASCII CHARACTERS ...... 0-127 (NORMAL) ..... 128-255 (EXTENDED)
    // http://www.flexcomm.com/library/ASCII256.htm
    public void using_Boolean_Array (String s){        
        
        boolean nonUniqueCharFoundFlag = false;
        boolean arr[] = new boolean[256];
        
        if (s.length()>256)
            System.out.println("Not unique Char");
        
        else{
            int i = 0;
            for (i=0;i<=s.length()-1;i++){
                int asciiVal = s.charAt(i);
                if(arr[asciiVal]==true){
                    System.out.println("Not unique Char");
                    nonUniqueCharFoundFlag = true;
                    break;
                }
                arr[asciiVal] = true;
            }
            if (nonUniqueCharFoundFlag == false)
                System.out.println("Unique Char");
        }
    }
    
        
        ///  IF ALL THE CHARS OF THE STRING CONTAIN ONLY FROM a-z OR from A-Z....
        //// we can use a 8-bit integer as the checker and then use BIT - MAnipulation    
        
        public void using_integer_checker (String s){
            
            boolean nonUniqueCharFoundFlag = false;
            int checker = 0;
            for (int i = 0 ; i<s.length();i++){
                int calcVal = s.charAt(i) - 'a';
                if ( (checker  & (1<<calcVal)) > 0 ){
                    System.out.println("Nor unique ");
                    nonUniqueCharFoundFlag = true;
                    break;
                }
                checker = checker | (1<<calcVal);
            }
            if (nonUniqueCharFoundFlag == false)
                System.out.println("Unique Char");
            
        }
        
          
}
    

