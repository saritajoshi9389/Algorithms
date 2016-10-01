/*
 Problem: Rotate an array of n elements to the right by k steps. 
For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 */
package arrays;

/**
 *
 * @author Sharmo
 */
public class RotateArray {
    
    public void rotate(int length , int steps){
        
        int[] arr = new int[length];
        int i = 0;
        while(i<length){
            arr[i]=i;
            i++;
        }
        System.out.println("INPUT ARRAY :::: ");
        i = 0;
        while(i<length){
            System.out.print(arr[i]+"\t");
            i++;
        }
        System.out.println();
        
        for(i=0;i<steps;i++){
            
            int lastElement = arr[length-1];
            
            for (int j=length-1;j>0;j--){
               // System.out.println("xx");
               int tmp = arr[j];
               arr[j]=arr[j-1];               
            }
            
           arr[0]= lastElement;   
           
        }
        System.out.println("OUTPUT ARRAY :::: ");
        i = 0;
        while(i<length){
            System.out.print(arr[i]+"\t");
            i++;
        }
        
        
    }
    
    
    /// ANOTHER SOLUTION ::::
    /*
    we do this in O(1) space and in O(n) time
    {1,2,3,4,5,6} and order 2  (ORDER IS STEPS here)
    1. Divide the array two parts: 1,2,3,4 and 5, 6
    2. Rotate first part: 4,3,2,1,5,6
    3. Rotate second part: 4,3,2,1,6,5
    4. Rotate the whole array: 5,6,1,2,3,4
    
    */
    public static void rotate(int[] arr, int order) {
	order = order % arr.length;
 
	if (arr == null || order < 0) {
		throw new IllegalArgumentException("Illegal argument!");
	}
 
	//length of first part
	int a = arr.length - order; 
 
	reverse(arr, 0, a-1);
	reverse(arr, a, arr.length-1);
	reverse(arr, 0, arr.length-1);
 
}
 
public static void reverse(int[] arr, int left, int right){
	if(arr == null || arr.length == 1) 
		return;
 
	while(left < right){
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
		left++;
		right--;
	}	
}
    
    
}
