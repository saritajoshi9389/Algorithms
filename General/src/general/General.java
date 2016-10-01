/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.util.ArrayList;

/**
 *
 * @author sarita
 */
public class General {
    
    public static void main(String[] args) {
        int number = 15;
        //int a[]=new int[5];
        ArrayList a = new ArrayList();
        while(number!=0){
            a.add(number%2);
            number = number/2;  
        }
        for (int i =a.size()-1;i>=0;i--){
            System.out.println(a.remove(i));
            
        }
    }
    
}
