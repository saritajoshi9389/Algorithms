/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package general_qs;

/**
 *
 * @author Sharmo
 */
public class Kth_Element {
    
    
    public int kthSmallest (int[] a, int left, int right ,int k){
        
        // partion position
        int pos = partition(a, left, right);
        if (pos == k-1)
            return a[pos];
        
        if(pos>k-1)
            return kthSmallest(a, left, pos-1, k);
        
        else
            return kthSmallest(a, pos+1, right, k);
        
    }
    
    // p = starting of the sub array which has to be partitioned
    // r = last position of the subarray 
    // this function returns the final position of the pivot for the input array arr
    public int partition(int[] arr, int p , int r){
        
        int finalpivotIndx;
        
        // always consider the last element to be the pivot
        int pivot = arr[r];
        // i is the pointer for elelmets which are less than the pivot
        int i = p-1;
        // j is the pointer for the elemts which are more than the pivot
        int j = p;
        // looping j over all the elements which are at positions from p to r-1
        for (j=p;j<=r-1;j++){
            
            // the concept is , at any iteration of this for loop, all the elements from p to current i, will be 
            // smaller than the pivot , and all elemts from i+1 to j will be greater than the pivot 
            if(arr[j]<pivot){
                i = i+1;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;                
            }
        }
        // now we know that the arr[p...r-1] is partitioned around arr[i], ( arr[i] being the last of the smaller elements wrt the pivot
        // so we needa place the pivot at arr[i+1], thus exchanges the places for pivot and arr[i+1]
        finalpivotIndx = i+1;
        
        int tmp2 = arr[finalpivotIndx];
        arr[finalpivotIndx] = pivot;
        arr[r] = tmp2;         
        
        return finalpivotIndx;
    }
    
    public static void main(String args[]){
        
        int arr[] = {12, 3, 5, 7, 4, 19, 26};
        int n = arr.length  ,  k= 1;
        Kth_Element obj = new Kth_Element();
        System.out.println(" Kth Smallest Element is ::" + obj.kthSmallest(arr,0,n-1,k));
    }
    
}
