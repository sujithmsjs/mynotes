
package coll.stack;

import java.util.Arrays;
import java.util.Stack;
import java.util.Vector;
import util.obj.ReadyData;

public class Stack_Demo {


    public static void main(String[] args) {
        
        String[] data = ReadyData.getStrings(10);
        Vector<String> vect = new Vector(Arrays.asList(data));
        
        Stack<String> stk = new Stack<>();
        for (String string : vect) {
            stk.push(string);
        }
        System.out.println("stk = " + stk);
    }
    
}
