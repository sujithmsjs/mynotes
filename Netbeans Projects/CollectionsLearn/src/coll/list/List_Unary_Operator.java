package coll.list;

import coll.util.CPrinter;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import util.obj.Box;
import util.obj.ReadyData;


public class List_Unary_Operator {
    public static void main(String[] args) {
        ArrayList<Box> al = ReadyData.getBoxData();
        
        System.out.println("\nBefore Unary apply.");
        CPrinter.printAL(al);
        
        // Used to apply a function to all the iteams at a time.
        al.replaceAll(new UnaryOperator() {
            @Override
            public Object apply(Object obj) {
                Box box = (Box) obj;
                box.setItem(box.getItem()+" Updated");
                box.setCount(box.getCount()+100);
                return box;
            }
        });
        
        System.out.println("\nAfter Unary apply.");
        CPrinter.printAL(al);
    }
    
}
