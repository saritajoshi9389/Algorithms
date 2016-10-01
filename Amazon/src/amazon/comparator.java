/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author sarit
 */
public class comparator {
    
public static void  chk(){
    String[] fruits = {"Orange", "Grape", "Apple", "Lemon", "Banana"};
    Arrays.sort(fruits);
    System.out.println(Arrays.toString(fruits));
    Arrays.sort(fruits, Collections.reverseOrder());
    System.out.println(Arrays.toString(fruits));
    }

public static void chkObj(){
    HashMap<String, Integer> map1 = new HashMap<String, Integer>();
    HashMap<String, Integer> map2 = new HashMap<String, Integer>();
}

public static void main(String args[])
{
    chk();
    
}
}

