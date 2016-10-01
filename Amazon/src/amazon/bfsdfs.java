/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon;

/**
 *
 * @author sarit
 */
public class bfsdfs {
    static int[] fib = new int[6];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            fibonacci(5);
            for (int i=0; i<fib.length;i++)
                System.out.println(fib[i]);

    }
    
    public  static int fibonacci(int i){    
    if(i==0) return 0;
    if(i==1) return 1;
    if(fib[i]!=0) return fib[i];
    fib[i] = fibonacci(i-1)+fibonacci(i-2);
    return fib[i];
    }
}

