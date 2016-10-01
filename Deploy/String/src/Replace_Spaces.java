/*
 Write a method to replace all spaces in a string with ‘%20’. consider 
 the original length of the String (excluding the spaces) is given
 and the space required for the new characters are given @ the end of the string.
 */

/**
 *
 * @author Sharmo
 */
public class Replace_Spaces {
    
    public static void main (String args[]){
        Replace_Spaces obj = new Replace_Spaces();
        
        String str = "I am going    ";        
        obj.replaceSpaces_My (str,8);        
    }
    
    public void replaceSpaces_My(String str, int noOfNonSpaceChars){
        
        char[] arr = str.toCharArray();
        //we need this to loop over non trailing space chars
        int sizeOfTrimmedString; //= str.trim().length();
        int i=0;
        while(i< arr.length){
            if (arr[i] == ' '){
               // we need the index of the last non-space charcter
               // we converted this array to a sting, then trimmed the last trailing spaces and got the size
               sizeOfTrimmedString = String.valueOf(arr).trim().length(); 
               for (int j = sizeOfTrimmedString-1; j>=i ; j--){
                   arr[j+2] = arr[j];
               }
               arr[i]='%' ; arr [i+1] ='2' ; arr[i+2] = '0';
               i = i+3;
            }
            else
                i = i+1;
        }
        
        str = String.valueOf(arr);
        System.out.println(str);
    }
    
    
    
}
