package coll.list;

import coll.util.CPrinter;
import java.util.ArrayList;
import java.util.Collections;
import util.obj.Box;
import util.obj.ReadyData;

public class Sort_And_Shuffle_List {
    
    
    public static void main(String[] args) {
        ArrayList<Box> al = ReadyData.getBoxData();
        
        //Print ArrayList Elements.
        System.out.println("Before sort");
        CPrinter.printAL(al);
        
        
        
        // Sort elements using List sort method.
        al.sort(al.get(0)); 
       
        
        System.out.println("After sort");
        CPrinter.printAL(al);
        
        //Shuffle elements
        Collections.shuffle(al);
        
        System.out.println("After shuffle elements");
        CPrinter.printAL(al);
        
        
        //Sort element using Collection sort method.
        Collections.sort(al, new Box("",0));
        
        System.out.println("After 2nd sort");
        CPrinter.printAL(al);
    }
    

    
}
